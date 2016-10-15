package de.tomsu.springtests;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ImageBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.file.Files;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.ReflectionUtils;

@SpringBootApplication
public class GsIntegrationApplication{
	
	@Bean
	DefaultFtpSessionFactory defaultFtpSessionFactory(
			@Value("${ftp.port:2121}") int port,
			@Value("${ftp.username:daniel}") String username,
			@Value("${ftp.username:test}") String password) {
		DefaultFtpSessionFactory defaultFtpSessionFactory = new DefaultFtpSessionFactory();
		defaultFtpSessionFactory.setPort(port);
		defaultFtpSessionFactory.setUsername(username);
		defaultFtpSessionFactory.setPassword(password);
		return defaultFtpSessionFactory;
	}
	
	@Bean
	IntegrationFlow files(
			@Value("${input-directory:${HOME}/input}") File in,
			Environment environment,
			DefaultFtpSessionFactory ftpSessionFactory) {
		GenericTransformer<File, Message<String>> fileStringTransformer =  (File source) -> {
			try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				 PrintStream printStream = new PrintStream(baos)) {
					ImageBanner banner = new ImageBanner(new FileSystemResource(source));
					banner.printBanner(environment, getClass(), printStream);
					return MessageBuilder.withPayload(new String(baos.toByteArray()))
							.setHeaderIfAbsent(FileHeaders.FILENAME, source.getName())
							.build();
			} 
			catch(IOException ioe) {
				ReflectionUtils.rethrowRuntimeException(ioe);
			}
			return null;
		};
		return IntegrationFlows.from(
			Files.inboundAdapter(in).autoCreateDirectory(true)
									.preventDuplicates(true)
									.patternFilter("*.jpg"),
									poller -> poller.poller(pm -> pm.fixedRate(1000)))
				.transform(File.class, fileStringTransformer)
				.handleWithAdapter(adapters -> adapters.ftp(ftpSessionFactory)
						.remoteDirectory("")
						.fileNameGenerator(message -> {
							Object o = message.getHeaders().get(FileHeaders.FILENAME);
							String filename = String.class.cast(o);
							return filename.split("\\.")[0] + ".txt";
						})
					)
				.get();
	}

	public static void main(String[] args) {
		SpringApplication.run(GsIntegrationApplication.class, args);
	}
}
