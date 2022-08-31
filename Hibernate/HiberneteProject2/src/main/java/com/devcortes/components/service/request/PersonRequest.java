package com.devcortes.components.service.request;

public class PersonRequest {
	private String name;	
	
	private String surname;	
	
	private Integer passport;	
	
	private Integer address;
	
	public PersonRequest() {}

	public PersonRequest(String name, String surname, Integer passport, Integer address) {		
		this.name = name;
		this.surname = surname;
		this.passport = passport;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getPassport() {
		return passport;
	}

	public void setPassport(Integer passport) {
		this.passport = passport;
	}

	public Integer getAddress() {
		return address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}
	
	

}
