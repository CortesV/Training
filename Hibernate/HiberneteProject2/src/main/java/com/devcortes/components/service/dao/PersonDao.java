package com.devcortes.components.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devcortes.components.entity.Person;
import com.devcortes.components.interfaces.IPersonDao;
import com.devcortes.components.service.request.PersonRequest;
import com.devcortes.service.HibernateUtil;

@Repository
public class PersonDao implements IPersonDao{
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<Person> getAll() {
		Session session = null;
		ArrayList<Person> result = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			Query SQLQuery = session.createQuery("select person from Person person");
			result = (ArrayList<Person>)SQLQuery.list();			
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
			Person del = (Person) session.get(Person.class, id);
	        session.delete(del);	
	        session.getTransaction().commit();
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		} 
	}

	@Override
	public void update(Integer id, PersonRequest person) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			Person update = (Person) session.get(Person.class, id);
	        update.setName(person.getName());
	        update.setSurname(person.getSurname());
	        //update.setPassport(person.getPassport());
	        //update.setAddress(person.getAddress());
	        session.update(update);			
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		}
		
	}

	@Override
	public Person getById(Integer id) {
		Session session = null;
		Person result = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			result = (Person) session.get(Person.class, id);	
			
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		} 
        return result;
	}

	@Override
	public void add(PersonRequest person) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			session.save(new Person(person.getName(), person.getSurname()/*, person.getPassport(), person.getAddress()*/));		
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		}
	}

}
