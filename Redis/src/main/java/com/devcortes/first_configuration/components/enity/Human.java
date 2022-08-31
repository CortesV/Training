package com.devcortes.first_configuration.components.enity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cortes on 25.11.18.
 */

@RedisHash("Human")
public class Human implements Serializable {

	public enum Gender {
		MALE, FEMALE
	}

	@Id
	private String id;

	@Indexed
	private String name;

	@Indexed
	private String lastname;

	private Gender gender;

	private int grade;

	private Address address;

	private @Reference List<Human> children;

	public Human() {
	}

	public Human(String id, String name, Gender gender, int grade) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.grade = grade;
	}

	public Human(String name, String lastname, Gender gender) {
		this.name = name;
		this.lastname = lastname;
		this.gender = gender;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Human> getChildren() {
		return children;
	}

	public void setChildren(List<Human> children) {
		this.children = children;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Human human = (Human) o;

		if (grade != human.grade)
			return false;
		if (id != null ? !id.equals(human.id) : human.id != null)
			return false;
		if (name != null ? !name.equals(human.name) : human.name != null)
			return false;
		if (lastname != null ? !lastname.equals(human.lastname) : human.lastname != null)
			return false;
		return gender == human.gender;
	}

	@Override public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
		result = 31 * result + (gender != null ? gender.hashCode() : 0);
		result = 31 * result + grade;
		return result;
	}

	@Override
	public String toString() {
		return "Human{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", lastname='" + lastname + '\'' + ", gender=" + gender + ", grade=" + grade + '}';
	}
}
