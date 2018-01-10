package com.itheima.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.dao.BaseDao;
import com.itheima.domain.Customer;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz; //实际类型参数

	public BaseDaoImpl() {
		//得到子类对象的Class对象
		Class c = this.getClass();
		//得到父类参数化类型     
		Type type = c.getGenericSuperclass(); //  BaseDaoImpl<Customer>
		System.out.println(type);
		ParameterizedType pType = (ParameterizedType) type;
		//得到所有 实际类型参数
		Type[] types = pType.getActualTypeArguments();
		//实际类型参数转成Class类型
		this.clazz = (Class) types[0];
	}
	
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	//查询表中的总条数
	public int count(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());//设置count方法
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		return list.get(0).intValue();
	}

	@Override
	//查询分页数据
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
		detachedCriteria.setProjection(null);//清空数据
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria,(currentPage-1)*pageSize,pageSize);
		return list;
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+this.clazz.getSimpleName());
	}

}
