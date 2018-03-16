package com.usertracker.dao;

import java.util.List;

public interface GenericDAO<T> {
	public void insert(T entity);
	public void delete(int id);
	public void update(T entity);
	public T getById(int id);
	public List<T> getAll();
}
