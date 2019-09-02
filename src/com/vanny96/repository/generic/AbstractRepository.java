package com.vanny96.repository.generic;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRepository<T extends Serializable> {
	private Class<T> objectClass;
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public void setClass(Class<T> classToSet) {
		objectClass = classToSet;
	}
	
	public List<T> getAll() {

		Session session = sessionFactory.openSession();
				
		session.beginTransaction();
				
		List<T> list = session.createQuery("from "+objectClass.getName(), objectClass).getResultList();
				
		session.getTransaction().commit();
		session.close();
		
		return list;
	}
	
	public T getById(int id) {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		T element = session.find(objectClass, id);
		
		session.getTransaction().commit();
		session.close();
		
		return element;
	}
	
	public void save(T element) {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		session.save(element);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void remove(T element) {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		session.delete(element);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void update(T element) {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		session.update(element);
		
		session.getTransaction().commit();
		session.close();
	}
}
