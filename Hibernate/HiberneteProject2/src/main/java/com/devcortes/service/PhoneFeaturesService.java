package com.devcortes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcortes.components.entity.Phone;
import com.devcortes.components.entity.PhoneFeatures;
import com.devcortes.components.interfaces.IPhoneFeaturesDao;
import com.devcortes.components.service.request.PhoneFeatureRequest;
import com.devcortes.components.service.request.PhoneRequest;

@Service
public class PhoneFeaturesService {
	@Autowired
	IPhoneFeaturesDao iPhoneFeaturesDao;
	public List<PhoneFeatures> getAll() {		
		return iPhoneFeaturesDao.getAll();
	}
	
	public PhoneFeatures getById(Integer id){
		return iPhoneFeaturesDao.getById(id);
	}
	
	public void delete(Integer id){
		iPhoneFeaturesDao.delete(id);
	}
	
	public void update(Integer id, PhoneFeatureRequest phone){
		iPhoneFeaturesDao.update(id, phone);
	}
	
	public void add(PhoneFeatureRequest phone){
		iPhoneFeaturesDao.add(phone);
	}
}
