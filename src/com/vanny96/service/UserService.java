package com.vanny96.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vanny96.model.User;
import com.vanny96.repository.UserDao;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public List<User> getAll(){
		return userDao.getAll();
	}
	
	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.save(user);
	}

	public User getById(int id) {
		return userDao.getById(id);
	}

	public void delete(User user) {
		userDao.remove(user);
	}
	
	public void update(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.update(user);
	}
	
	public User getByUsername(String username){
		return userDao.getByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getByUsername(username);
		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
																.password(user.getPassword())
																.roles("USER").build();
	}

	
}
