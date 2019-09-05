package com.vanny96.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.vanny96.model.User;
import com.vanny96.repository.UserDao;

@Component
public class IdControlBean {
	
	@Autowired
	private UserDao userDao;

	public boolean checkId(Authentication authentication, int id){
		String username = authentication.getName();
		User user = userDao.getByUsername(username);
		
		return user != null && user.getId() == id;
	}
}
