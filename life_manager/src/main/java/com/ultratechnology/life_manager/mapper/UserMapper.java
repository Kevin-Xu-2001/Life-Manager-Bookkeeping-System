package com.ultratechnology.life_manager.mapper;

import com.ultratechnology.life_manager.entity.User;

public interface UserMapper {

	/**
	 * insert user into the database
	 * @param user
	 * @return
	 */
	Integer insert(User user);
	
	/**
	 * find the user object by id
	 * @param id
	 * @return
	 */
	User findById(Integer id);
	
	/**
	 * find the user object by username
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	
}
