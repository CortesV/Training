package com.devcortes.controller;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devcortes.components.entity.Phone;
import com.devcortes.service.PhoneService;

@RestController
@RequestMapping(value = "/phone2")
public class PhoneSecondController {
	@Autowired
	PhoneService phoneService;
	
	@RequestMapping(value="/get", method = RequestMethod.GET)	
	public List<Phone> getAll(){	
		DetachedCriteria detachedCriteria = null;
       return phoneService.getAll(detachedCriteria);
	}
}
