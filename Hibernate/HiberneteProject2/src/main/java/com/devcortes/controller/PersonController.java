package com.devcortes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devcortes.components.entity.Person;
import com.devcortes.components.service.request.PersonRequest;
import com.devcortes.service.PersonService;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
	@Autowired
	PersonService personService;
	
	@RequestMapping(value="/get", method = RequestMethod.GET)	
	public List<Person> getAll(){			
       return personService.getAll();
	}
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Person get(@PathVariable("id") Integer id){			
       return personService.getById(id);
	}
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public void add(@RequestBody PersonRequest person){
		personService.add(person);
	}
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)	
	public void delete(@PathVariable("id") Integer id){
		personService.delete(id);		
	}
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)	
	public void update(@PathVariable("id") Integer id, @RequestBody PersonRequest person){		
		personService.update(id, person);
	}
	
}
