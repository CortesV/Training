package com.devcortes.controller;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devcortes.components.entity.Address;
import com.devcortes.components.entity.Phone;
import com.devcortes.components.service.request.AddressRequest;
import com.devcortes.components.service.request.PhoneRequest;
import com.devcortes.service.PhoneService;

@RestController
@RequestMapping(value = "/phone")
public class PhoneController {
	@Autowired
	PhoneService phoneService;
	
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET)	
	public List<Phone> getAll(@PathVariable("id") Integer id){	
	   DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Phone.class)
				.createAlias("phoneFeatures", "feature")
				.add(Restrictions.eq("feature.id", id))	;			
       return phoneService.getAll(detachedCriteria);
	}
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Phone get(@PathVariable("id") Integer id){			
       return phoneService.getById(id);
	}
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public void add(@RequestBody PhoneRequest phone){
		phoneService.add(phone);
	}
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)	
	public void delete(@PathVariable("id") Integer id){
		phoneService.delete(id);		
	}
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)	
	public void update(@PathVariable("id") Integer id, @RequestBody PhoneRequest phone){		
		phoneService.update(id, phone);
	}
}
