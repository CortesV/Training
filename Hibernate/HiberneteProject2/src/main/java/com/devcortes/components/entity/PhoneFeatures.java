package com.devcortes.components.entity;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "features", catalog = "system")
@Audited
public class PhoneFeatures {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "idFeatures")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFeatures;	
	
	private String ram;
	
	private String hdd;
	
	private Integer mainCamera;
	
	private Integer frontalCamera;
	
	

	public PhoneFeatures() {}
	

	public PhoneFeatures(String ram, String hdd, Integer mainCamera, Integer frontalCamera) {
		this.ram = ram;
		this.hdd = hdd;
		this.mainCamera = mainCamera;
		this.frontalCamera = frontalCamera;
	}


	public Integer getIdFeatures() {
		return idFeatures;
	}

	public void setIdFeatures(Integer idFeatures) {
		this.idFeatures = idFeatures;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getHdd() {
		return hdd;
	}

	public void setHdd(String hdd) {
		this.hdd = hdd;
	}

	public Integer getMainCamera() {
		return mainCamera;
	}

	public void setMainCamera(Integer mainCamera) {
		this.mainCamera = mainCamera;
	}

	public Integer getFrontalCamera() {
		return frontalCamera;
	}

	public void setFrontalCamera(Integer frontalCamera) {
		this.frontalCamera = frontalCamera;
	}


	
	
	
	
	
}
