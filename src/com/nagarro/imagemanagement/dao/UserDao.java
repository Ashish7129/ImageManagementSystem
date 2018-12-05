package com.nagarro.imagemanagement.dao;

import com.nagarro.imagemanagement.model.User;
/**
 * This is a User DAO Interface which contains unimplemented methods
 * 
 * @author ashishaggarwal
 *
 */
public interface UserDao {
	/**
	 * Get the user by the user object
	 * @param user
	 * @return
	 */
	boolean getUser(User user);
}
