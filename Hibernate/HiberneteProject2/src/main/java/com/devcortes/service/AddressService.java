package com.devcortes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcortes.components.entity.Address;
import com.devcortes.components.interfaces.IAddressDao;
import com.devcortes.components.service.request.AddressRequest;


@Service
public class AddressService {
	@Autowired
	IAddressDao iAddressDao;
	
	public List<Address> getAll() {		
		return iAddressDao.getAll();
	}
	
	public Address getById(Integer id){
		return iAddressDao.getById(id);
	}
	
	public void delete(Integer id){
		iAddressDao.delete(id);
	}
	
	public void update(Integer id, AddressRequest address){
		iAddressDao.update(id, address);
	}
	
	public void add(AddressRequest address){
		iAddressDao.add(address);
	}
}
