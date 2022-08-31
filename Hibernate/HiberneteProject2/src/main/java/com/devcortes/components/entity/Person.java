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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "person", catalog = "system")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	private String name;	
	
	private String surname;	
	
	
	@NotAudited
	@OneToOne
	@JoinColumn(name="passport")
	@JsonBackReference
	private Passport pass;	
	
	@NotAudited
	@OneToOne
    @JoinColumn(name = "address")
	@JsonBackReference
	private Address address;
	
	@NotAudited
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pers", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Phone> phones = new ArrayList<>();

	public Person() {}

	public Person(String name, String surname) {		
		this.name = name;
		this.surname = surname;
		
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Passport getPassport() {
		return pass;
	}

	public void setPassport(Passport passport) {
		this.pass = passport;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	
		
	
}
