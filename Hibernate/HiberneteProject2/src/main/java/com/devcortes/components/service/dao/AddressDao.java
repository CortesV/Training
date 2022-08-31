package com.devcortes.components.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devcortes.components.entity.Address;
import com.devcortes.components.interfaces.IAddressDao;
import com.devcortes.components.service.request.AddressRequest;
import com.devcortes.service.HibernateUtil;

@Repository
public class AddressDao implements IAddressDao{	
	
	
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<Address> getAll() {
		Session session = null;
		ArrayList<Address> result = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query SQLQuery = session.createQuery("select address from Address address");
			result = (ArrayList<Address>)SQLQuery.list();		
			session.getTransaction().commit();
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		} 
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void delete(Integer id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction(); 
			Address del = (Address) session.get(Address.class, id);
	        session.delete(del);		
	        session.getTransaction().commit();
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		} 
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void update(Integer id, AddressRequest address) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();	
			session.beginTransaction();
			Address update = (Address) session.get(Address.class, id);
	        update.setAddress(address.getAddress());
	        update.setCity(address.getCity());
	        update.setPerson(address.getPerson());
	        session.update(update);		
	        session.getTransaction().commit();
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public Address getById(Integer id) {
		Session session = null;
		Address result = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();	
			session.beginTransaction();
			result = (Address) session.get(Address.class, id);	
			session.getTransaction().commit();
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		} 
        return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void add(AddressRequest address) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();	
			session.beginTransaction();
			session.save(new Address(address.getAddress(), address.getCity(), address.getPerson()));
			session.getTransaction().commit();
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		}
		
	}

}
