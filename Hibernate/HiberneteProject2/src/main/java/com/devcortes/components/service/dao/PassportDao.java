package com.devcortes.components.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.devcortes.components.entity.Passport;
import com.devcortes.components.entity.Person;
import com.devcortes.components.interfaces.IPassportDao;
import com.devcortes.components.service.request.PassportRequest;
import com.devcortes.service.HibernateUtil;

@Repository
public class PassportDao implements IPassportDao{

	@Override
	public List<Passport> getAll() {
		Session session = null;
		ArrayList<Passport> result = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			Query SQLQuery = session.createQuery("select passport from Passport passport");
			result = (ArrayList<Passport>)SQLQuery.list();			
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		} 
		return result;
	}

	@Override
	public void delete(Integer id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();		
			session.beginTransaction(); 
			Passport del = (Passport) session.get(Passport.class, id);
	        session.delete(del);	
	        session.getTransaction().commit();
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		} 
	}

	@Override
	public void update(Integer id, PassportRequest passport) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			Passport update = (Passport) session.get(Passport.class, id);
	        update.setCode(passport.getCode());        
	        session.update(update);			
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		}
	}

	@Override
	public Passport getById(Integer id) {
		Session session = null;
		Passport result = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			result = (Passport) session.get(Passport.class, id);	
			
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		} 
        return result;
	}

	@Override
	public void add(PassportRequest passport) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			session.save(new Passport(passport.getCode()));		
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		}
	}

}
