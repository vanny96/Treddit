package com.vanny96.repository.generic;

import java.util.List;

public interface RepositoryInterface<T> {
	public List<T> getAll();
	public T getById(int id);
	public void save(T element);
	public void remove(T element);
	public void update(T element);
}
