package com.devcortes.first_configuration.components.enity;

import java.io.Serializable;

/**
 * Created by cortes on 25.11.18.
 */
public class Employee implements Serializable {

	private static final long serialVersionUID = 1603714798906422731L;
	private String id;
	private String name;
	private String department;

	public Employee() {
	}

	public Employee(String id, String name, String department) {
		this.id = id;
		this.name = name;
		this.department = department;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
