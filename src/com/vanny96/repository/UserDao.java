package com.vanny96.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.vanny96.model.User;
import com.vanny96.repository.generic.GenericDao;

@Repository
public class UserDao extends GenericDao<User> implements UserInterface{
	
	public UserDao() {
		super.setClass(User.class);
	}
	
	public User getByUsername(String username) {
		Session session = super.sessionFactory.openSession();
		User user = null;
		
		session.beginTransaction();
		
		try {
		user = session.createQuery("from User where username = :username", User.class)
								.setParameter("username", username)
								.getResultList().get(0);
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		session.getTransaction().commit();
		session.close();
		
		return user;
	}
}
