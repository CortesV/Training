package com.devcortes.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devcortes.components.entity.Address;
import com.devcortes.components.service.request.AddressRequest;
import com.devcortes.service.AddressService;



@RestController
@RequestMapping(value = "/address")
public class AddressController {
	@Autowired
	AddressService addressService;	
	
	public AddressController() {	
		
		System.out.println("java constr");
	}
	
	@PostConstruct
	public void init(){
		System.out.println("postcostr");
		
	}
	
	@RequestMapping(value="/get", method = RequestMethod.GET)	
	public List<Address> getAll(){			
       return addressService.getAll();
	}
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Address get(@PathVariable("id") Integer id){			
       return addressService.getById(id);
	}
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public void add(@RequestBody AddressRequest address){
		addressService.add(address);
	}
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)	
	public void delete(@PathVariable("id") Integer id){
		addressService.delete(id);		
	}
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)	
	public void update(@PathVariable("id") Integer id, @RequestBody AddressRequest address){		
		addressService.update(id, address);
	}
}
