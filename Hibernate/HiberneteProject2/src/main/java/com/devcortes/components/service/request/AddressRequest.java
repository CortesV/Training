package com.devcortes.components.service.request;

public class AddressRequest {
	private String address;
	
	private String city;
	
	private Integer person;

	public AddressRequest() {}
	
	public AddressRequest(String address, String city, Integer person) {
		this.address = address;
		this.city = city;
		this.person = person;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPerson() {
		return person;
	}

	public void setPerson(Integer person) {
		this.person = person;
	}
	
	
}
