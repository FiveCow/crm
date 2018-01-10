package com.itheima.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.Customer;

public interface BaseDao<T> {
	
	public void save(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
	public T findById(Serializable id);
	
	public int count(DetachedCriteria detachedCriteria);

	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

	public List<T> findAll();
	
}
