package com.tinderbot.utils;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.tinderbot.entities.User;
import com.tinderbot.services.UserService;

public class Authentication {
	
	private static Logger LOGGER = Logger.getLogger(Authentication.class);
	
	@Autowired
	private static UserService userService;
	
	public static JSONObject setNotAuthenticated(User user) throws JSONException {
		userService.setAuthenticated(user, false);
		LOGGER.info(user.getTinderId() + " is not authenticated");
		JSONObject json = new JSONObject();
		json.accumulate("code", 401);
		return json;
	}

}
