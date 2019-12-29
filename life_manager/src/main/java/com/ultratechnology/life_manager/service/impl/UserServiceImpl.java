package com.ultratechnology.life_manager.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.ultratechnology.life_manager.entity.User;
import com.ultratechnology.life_manager.mapper.UserMapper;
import com.ultratechnology.life_manager.service.IUserService;
import com.ultratechnology.life_manager.service.exception.DuplicateKeyException;
import com.ultratechnology.life_manager.service.exception.PasswordNotMatchException;
import com.ultratechnology.life_manager.service.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserMapper mapper;
	
	
	/**
	 * input the original password and return the md5 result
	 * @param srcPassword
	 * @param salt
	 * @return
	 */
	private String getMd5Password(String srcPassword, String salt) {
		//The rule is defined by yourself
		String str = salt + srcPassword + salt;
		//Loop 10 times of computation
		for (int i = 0; i < 10; i++) {
			str = DigestUtils
				.md5DigestAsHex(str.getBytes())
					.toUpperCase();
		}
		//return the md5 result
		return str;
	}
	
	
	/**
	 * Find the user object by id
	 * @param username
	 * @return
	 */
	private User findByUsername(String username) {
		return mapper.findByUsername(username);
	}
	
	
	/**
	 * Find the user object by id
	 * @param id
	 * @return
	 */
	private User findById(Integer id) {
		return mapper.findById(id);
	}
	
	
	/**
	 * User login method
	 */
	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		//Search for user by username
		User data = findByUsername(username);
		//Judge if the result is null
		if (data == null) {
			//If it is, the user Does Not Exist
			throw new UserNotFoundException(
				"Fail to login! The username does not exist.");
		}
		
		//If user exists, get the salt from it
		String salt = data.getSalt();
		//Do the same encryption computation
		String md5Password 
			= getMd5Password(password, salt);
		
		//Judge if the encrypted password is right
		if (data.getPassword().equals(md5Password)) {
			//If yes, judge if the user is deleted
			if (data.getIs_delete() == 1) {
				//If is deleted, throw an exception
				throw new UserNotFoundException(
					"Fail to login! Your user is deleted.");
			}
			
			//If not, login success, set the sensitive password info to null
			data.setPassword(null);
			data.setSalt(null);
			//Return user data
			return data;
		}else {
			//If the password is not right, throw the exception
			throw new PasswordNotMatchException(
				"Fail to login! Password is wrong.");
		}
	}
	
	
	/**
	 * Add new users
	 */
	public User addnew(User user) {
		//search the user by the username inputed
		User data = findByUsername(user.getUsername());
		//judge if the result is null
		if (data == null) {
			//if true: No such a user, allow register
			//add the logging data
			
			//is not deleted
			user.setIs_delete(0); 
			//other 4 logging data
			Date now = new Date();
			user.setCreated_user(user.getUsername());
			user.setCreated_time(now);
			user.setModified_user(user.getUsername());
			user.setModified_time(now);
			// -----------------------
			//deal with the password by incryption
			//1.get random UUID as a salt
			String salt = UUID.randomUUID().toString().toUpperCase();
			System.out.println("!!!!The Salt is: " + salt);
			//2.get the original password
			String srcPassword = user.getPassword();
			//3.operate the encryption computation
			String md5Password = getMd5Password(srcPassword, salt);
			System.out.println("!!!!The md5 Password is: " + md5Password);
			//4.seal the password into the User object
			user.setPassword(md5Password);
			//5.seal the salt into the User object
			user.setSalt(salt);
			//operate the insert new user method
			mapper.insert(user);
			return user;
		} else {
			//If the username already exists, throw an exception
			throw new DuplicateKeyException(
					"Fail to register！The username your are trying to register (" + user.getUsername() + ") already exists！");
		}
	}
	
	
}
