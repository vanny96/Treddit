package com.vanny96.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.vanny96.model.User;
import com.vanny96.repository.UserDao;

@Component
public class CheckAuth {

	@Autowired
	private UserDao userDao;
	
	public boolean checkId(Authentication auth, int id) {
		User user = userDao.getByUsername(auth.getName());
		
		System.out.println("In checkAuth, id requested is "+id+" user is "+ user);
		return user != null && user.getId() == id;
	}
}
