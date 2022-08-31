package com.devcortes.components.interfaces;

import java.util.List;

import com.devcortes.components.entity.Person;
import com.devcortes.components.service.request.PersonRequest;


public interface IPersonDao {
	public List<Person> getAll();
	public void delete(Integer id);
	public void update(Integer id, PersonRequest person);
	public Person getById(Integer id);
	public void add(PersonRequest person);		
}
