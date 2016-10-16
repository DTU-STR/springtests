package de.tomsu.springtests;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class GsFunctionalReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsFunctionalReactiveApplication.class, args);
	}
}

interface PersonRepository extends MongoRepository<Person, String> {
	
	@Query("{}")
	Stream<Person> all();
	
	CompletableFuture<Person> findById(String id);
	
};

@Document
class Person {
	
	public Person() {
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Id
	private String id;
	private String name;
	private int age;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}

@Component
class SampleDataCLR implements CommandLineRunner {
	
	private final PersonRepository personRepository;

	public SampleDataCLR(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		Stream.of("Person ", "Person C", "Person B", "Person D")
			.forEach(name -> personRepository.save(new Person(name, new Random(23876238236L).nextInt(100))));
		personRepository.findAll().forEach(System.out::println);
	}
	
}