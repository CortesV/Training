package com.devcortes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devcortes.components.entity.Passport;
import com.devcortes.components.service.request.PassportRequest;
import com.devcortes.service.PassportService;



@RestController
@RequestMapping(value = "/passport")
public class PassportController {
	@Autowired
	PassportService passportService;
	
	@RequestMapping(value="/get", method = RequestMethod.GET)	
	public List<Passport> getAll(){			
       return passportService.getAll();
	}
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Passport get(@PathVariable("id") Integer id){			
       return passportService.getById(id);
	}
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public void add(@RequestBody PassportRequest passport){
		passportService.add(passport);
	}
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)	
	public void delete(@PathVariable("id") Integer id){
		passportService.delete(id);		
	}
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)	
	public void update(@PathVariable("id") Integer id, @RequestBody PassportRequest passport){		
		passportService.update(id, passport);
	}
}
