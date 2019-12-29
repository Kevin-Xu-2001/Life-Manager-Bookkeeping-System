package com.ultratechnology.life_manager.service;

import com.ultratechnology.life_manager.entity.User;

public interface IUserService {

	/**
	 * The method to add a new user,
	 * if success, return the user object
	 * @param user
	 * @return
	 */
	User addnew(User user);
	
	
	/**
	 * The method to verfy the Identity of the user by checking login information
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username,String password);
}
