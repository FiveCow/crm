package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;
import com.itheima.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	//保存客户
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override//封装数据给PageBean
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
		//求总记录数
		int count = customerDao.count(detachedCriteria);
		//求总页数
		int totalPage =  (int) Math.ceil(count*1.0/pageSize); //count%pageSize==0?count/pageSize: count/pageSize+1;
		//求当前页的集合数据
		List<Customer> list = customerDao.findByPage(detachedCriteria,currentPage,pageSize);
		
		//封装数据给PageBean
		PageBean<Customer> pb = new PageBean<>();
		pb.setCount(count);
		pb.setCurrentPage(currentPage);
		pb.setList(list);
		pb.setPageSize(pageSize);
		pb.setTotalPage(totalPage);
		return pb;
	}

	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

}
