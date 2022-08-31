package com.devcortes.components.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "address", catalog = "system")
@Audited
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	private String address;
	
	private String city;
	
	private Integer person;
	
	@NotAudited
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "address", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Person> persons = new ArrayList<>();

	public Address() {}

	public Address(String address, String city, Integer person) {
		this.address = address;
		this.city = city;
		this.person = person;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	
	
	

}
