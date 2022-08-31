package com.devcortes.components.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.devcortes.components.entity.PhoneFeatures;
import com.devcortes.components.interfaces.IPhoneFeaturesDao;
import com.devcortes.components.service.request.PhoneFeatureRequest;
import com.devcortes.service.HibernateUtil;

@Repository
public class PhoneFeaturesDao implements IPhoneFeaturesDao{

	@Override
	public List<PhoneFeatures> getAll() {
		Session session = null;
		ArrayList<PhoneFeatures> result = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			result = (ArrayList<PhoneFeatures>)session.createCriteria(PhoneFeatures.class, "features")				
					.list();			
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		} 
		return result;	
	}

	@Override
	public List<PhoneFeatures> getPages(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Integer id, PhoneFeatureRequest phone) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhoneFeatures getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(PhoneFeatureRequest phone) {
		// TODO Auto-generated method stub
		
	}

}
