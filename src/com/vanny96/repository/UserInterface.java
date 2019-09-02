package com.vanny96.repository;

import com.vanny96.model.User;
import com.vanny96.repository.generic.RepositoryInterface;

public interface UserInterface extends RepositoryInterface<User>{
	public User getByUsername(String username);
}
