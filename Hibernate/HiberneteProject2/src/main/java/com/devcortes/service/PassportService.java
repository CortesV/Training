package com.devcortes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcortes.components.entity.Passport;
import com.devcortes.components.interfaces.IPassportDao;
import com.devcortes.components.service.request.PassportRequest;


@Service
public class PassportService {
	@Autowired
	IPassportDao iPassportDao;
	
	public List<Passport> getAll() {		
		return iPassportDao.getAll();
	}
	
	public Passport getById(Integer id){
		return iPassportDao.getById(id);
	}
	
	public void delete(Integer id){
		iPassportDao.delete(id);
	}
	
	public void update(Integer id, PassportRequest passport){
		iPassportDao.update(id, passport);
	}
	
	public void add(PassportRequest passport){
		iPassportDao.add(passport);
	}	
}
