package com.devcortes.components.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.devcortes.components.entity.Person;
import com.devcortes.components.entity.Phone;
import com.devcortes.components.interfaces.IPhoneDao;
import com.devcortes.components.service.request.PhoneRequest;
import com.devcortes.service.HibernateUtil;

@Repository("first")
public class PhoneDao implements IPhoneDao{

	@Override
	public List<Phone> getAll(DetachedCriteria detachedCriteria) {			
		Session session = null;
		ArrayList<Phone> result = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			Query SQLQuery = session.createQuery("select phone from Phone phone");
			result = (ArrayList<Phone>)SQLQuery.list();			
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
			Phone del = (Phone) session.get(Phone.class, id);
	        session.delete(del);		
	        session.getTransaction().commit();
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		} 
	}

	@Override
	public void update(Integer id, PhoneRequest phone) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			Phone update = (Phone) session.get(Phone.class, id);
	        update.setProducer(phone.getProducer());
	        update.setModel(phone.getModel());
	        update.setNumber(phone.getNumber());	        
	        session.update(update);			
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		}
	}

	@Override
	public Phone getById(Integer id) {
		Session session = null;
		Phone result = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			result = (Phone) session.get(Phone.class, id);	
			
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		} 
        return result;
	}

	@Override
	public void add(PhoneRequest phone) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			session.save(new Phone(phone.getProducer(), phone.getModel(), phone.getNumber()));		
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		}		
	}

	@Override
	public List<Phone> getPages(Integer id) {		
		Session session = null;
		ArrayList<Phone> result = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			result = (ArrayList<Phone>)session.createCriteria(Phone.class)
					 .setFirstResult(0)
					 .setMaxResults(5)
					 .list();			
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		} 
		return result;
	}
	
	/*public  List<Phone> restrict(){
		DetachedCriteria query = DetachedCriteria.forClass(Phone.class)
				 .add(Restrictions.eq("producer", "qwerty"))
				 .add(Restrictions.eq("model", "sdf"))
				 .add(Restrictions.between("owner", 1, 9))					 
				 .addOrder( Order.desc("owner") );
		Session session = null;
		ArrayList<Phone> result = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			result = (ArrayList<Phone>)query.getExecutableCriteria(session).list();			
		} finally {
			if (session.isOpen()) {
	            session.close();
	        }
		} 
		return result;
	}*/

}
