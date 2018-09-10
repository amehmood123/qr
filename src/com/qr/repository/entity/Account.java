package com.qr.repository.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;
	private String email;
	private String city;

	private Set<Contact> contacts = new HashSet<Contact>(0);

	public Account() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
}