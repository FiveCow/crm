package com.itheima.service;

import java.util.List;

import com.itheima.domain.User;

public interface UserService {

	void regist(User user);

	User login(User user);

	List<User> findAll();

}
