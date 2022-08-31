package com.devcortes.components.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "phone", catalog = "system")
public class Phone implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "idPhone")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPhone;	
	
	private String producer;
	
	private String model;
	
	private String number;		

	@NotAudited
	@ManyToOne
    @JoinColumn(name = "idFeatures")
	@JsonManagedReference
    private PhoneFeatures phoneFeatures; 
	
	@NotAudited
	@ManyToOne
    @JoinColumn(name = "owner")
	@JsonBackReference
	private Person pers;
	

	public Phone() {}

	public Phone(String producer, String model, String number) {		
		this.producer = producer;
		this.model = model;
		this.number = number;			
	}

	public Integer getIdPhone() {
		return idPhone;
	}

	public void setIdPhone(Integer idPhone) {
		this.idPhone = idPhone;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	

	public PhoneFeatures getPhoneFeatures() {
		return phoneFeatures;
	}

	public void setPhoneFeatures(PhoneFeatures phoneFeatures) {
		this.phoneFeatures = phoneFeatures;
	}	
	

	public Person getPers() {
		return pers;
	}

	public void setPers(Person pers) {
		this.pers = pers;
	}
	
	
	
	
}
