package com.devcortes.components.interfaces;

import java.util.List;

import com.devcortes.components.entity.PhoneFeatures;
import com.devcortes.components.service.request.PhoneFeatureRequest;



public interface IPhoneFeaturesDao {
	public List<PhoneFeatures> getAll();
	public List<PhoneFeatures> getPages(Integer id);
	public void delete(Integer id);
	public void update(Integer id, PhoneFeatureRequest phone);
	public PhoneFeatures getById(Integer id);
	public void add(PhoneFeatureRequest phone);		
}
