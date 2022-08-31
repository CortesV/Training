package com.devcortes.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.devcortes.components.entity.Phone;
import com.devcortes.components.interfaces.IPhoneDao;
import com.devcortes.components.service.request.PhoneRequest;

@Service
public class PhoneSecondService {
	@Autowired
	@Qualifier("second")
	IPhoneDao iPhoneDao;
	
	public List<Phone> getAll(DetachedCriteria detachedCriteria) {		
		return iPhoneDao.getAll(detachedCriteria);
	}
	
	public Phone getById(Integer id){
		return iPhoneDao.getById(id);
	}
	
	public void delete(Integer id){
		iPhoneDao.delete(id);
	}
	
	public void update(Integer id, PhoneRequest phone){
		iPhoneDao.update(id, phone);
	}
	
	public void add(PhoneRequest phone){
		iPhoneDao.add(phone);
	}
}
