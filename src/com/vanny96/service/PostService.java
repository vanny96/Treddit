package com.vanny96.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanny96.model.Post;
import com.vanny96.repository.generic.GenericDao;

@Service
public class PostService {
	
	private GenericDao<Post> postDao;
	
	@Autowired
	public PostService(GenericDao<Post> postDao) {
		postDao.setClass(Post.class);
		this.postDao = postDao;
	}
	
	public List<Post> getAll(){
		return postDao.getAll();
	}
	
	public Post getById(int id) {
		return postDao.getById(id);
	}
	
	public void save(Post post) {
		postDao.save(post);
	}

	public void delete(Post post) {
		postDao.remove(post);
	}
}
