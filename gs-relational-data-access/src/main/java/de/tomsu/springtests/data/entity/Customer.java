package de.tomsu.springtests.data.entity;

import java.util.Objects;
import java.util.function.Supplier;
import java.time.Instant;

public class Customer {
	
	private Long id;
	
	private String firstName, lastName;
	
	protected transient Supplier<String> messageSupplier = () -> "Name is  required. Error generated on  "
	          + Instant.now();;
	
	public Customer(Long id, String firstName, String lastName) {
		this.id = Objects.requireNonNull(id);
		this.firstName = firstName;
		this.lastName = Objects.requireNonNull(lastName, messageSupplier);
	}
	
	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

}
