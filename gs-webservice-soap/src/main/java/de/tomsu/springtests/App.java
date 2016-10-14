package de.tomsu.springtests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is my Spring Boot WS Application.
 * see https://spring.io/guides/gs/producing-web-service/
 *
 */
@SpringBootApplication
public class App {
    
	public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
