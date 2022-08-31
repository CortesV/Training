package com.devcortes.first_configuration.components.enity;

import java.io.Serializable;

/**
 * Created by cortes on 18.11.18.
 */
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int age;

	public Person() {
	}

	public Person(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		return id + " - " + name + " - " + age;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Person person = (Person) o;

		if (id != person.id)
			return false;
		if (age != person.age)
			return false;
		return name != null ? name.equals(person.name) : person.name == null;
	}

	@Override public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + age;
		return result;
	}
}
