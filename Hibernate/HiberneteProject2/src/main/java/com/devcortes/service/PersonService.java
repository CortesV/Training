package com.devcortes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcortes.components.entity.Person;
import com.devcortes.components.interfaces.IPersonDao;
import com.devcortes.components.service.request.PersonRequest;


@Service
public class PersonService {
	@Autowired
	IPersonDao iPersonDao;
	
	public List<Person> getAll() {		
		return iPersonDao.getAll();
	}
	
	public Person getById(Integer id){
		return iPersonDao.getById(id);
	}
	
	public void delete(Integer id){
		iPersonDao.delete(id);
	}
	
	public void update(Integer id, PersonRequest person){
		iPersonDao.update(id, person);
	}
	
	public void add(PersonRequest person){
		iPersonDao.add(person);
	}	
}
