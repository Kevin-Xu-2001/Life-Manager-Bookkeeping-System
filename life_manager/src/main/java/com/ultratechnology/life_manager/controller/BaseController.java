package com.ultratechnology.life_manager.controller;

import javax.servlet.http.HttpSession;

public abstract class BaseController {
	/**
	 * the correct response number
	 */
	public static final Integer SUCCESS = 200;
	
	public static final Integer FAIL = 500;
	
	/**
	 * Get the user id from session
	 * @param session HttpSession
	 * @return the id of the current user
	 */
	protected Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(
				session.getAttribute("uid").toString());
	}
}
