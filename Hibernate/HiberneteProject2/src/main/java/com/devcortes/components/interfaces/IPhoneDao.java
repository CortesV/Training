package com.devcortes.components.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.devcortes.components.entity.Phone;
import com.devcortes.components.service.request.PhoneRequest;



public interface IPhoneDao {
	public List<Phone> getAll(DetachedCriteria detachedCriteria);
	public List<Phone> getPages(Integer id);
	public void delete(Integer id);
	public void update(Integer id, PhoneRequest phone);
	public Phone getById(Integer id);
	public void add(PhoneRequest phone);		
}
