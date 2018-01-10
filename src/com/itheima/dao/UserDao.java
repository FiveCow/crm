package com.itheima.dao;

import java.util.List;

import com.itheima.domain.User;

public interface UserDao {

	void regist(User user);

	User login(User user);

	List<User> findAll();

}
