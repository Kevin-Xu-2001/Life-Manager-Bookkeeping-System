package com.ultratechnology.life_manager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ultratechnology.life_manager.entity.User;
import com.ultratechnology.life_manager.service.IUserService;
import com.ultratechnology.life_manager.util.ResponseResult;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;
	
	@PostMapping("/add.do")
	public ResponseResult<User> handleAdd(User user){
		System.out.println("UserController: handleAdd function");
		System.out.println("User  :"+user);
		User result = userService.addnew(user);
		ResponseResult<User> rr = new ResponseResult<User>(SUCCESS);
		rr.setData(result);
		return rr;
	}
	
	@PostMapping("/login.do")
	public ResponseResult<User> handleLogin(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session){
		//execute the login service
		try {
			User user = userService.login(username, password);
			//store the related information into the Session
			session.setAttribute("uid", user.getId());
			session.setAttribute("username", user.getUsername());
			System.out.println("Session uid is: "+getUidFromSession(session));
			return new ResponseResult<User>(SUCCESS,user);
		}catch (Exception e) {
			return new ResponseResult<>(FAIL,e.getMessage());
		}

	}
	
	@GetMapping("/getUname.do")
	public ResponseResult<Void> getUname(HttpSession session){
		String username = session.getAttribute("username").toString();
		return new ResponseResult<>(SUCCESS,username);
	}
	
	
	
	
}
