package com.nagarro.imagemanagement.service;

import com.nagarro.imagemanagement.dao.UserDao;
import com.nagarro.imagemanagement.daoimplementation.UserDaoImpl;
import com.nagarro.imagemanagement.model.User;
/**
 * UserService class helps the servlet classes to communicate with the Dao implementation methods 
 * 
 * @author ashishaggarwal
 *
 */
public class UserService {
	UserDao userDao = new UserDaoImpl(); 
	
	/*
	 * GetUser : get the user 
	 */
	public boolean getUser(User user) {
		return userDao.getUser(user);
	}

}
