package com.tinderbot.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tinderbot.entities.User;
import com.tinderbot.repositories.UserRepository;
import com.tinderbot.services.tinder.ITinderService;

@Controller("tinderBotAuthenticateUsers")
public class TinderBotAuthenticateUsers {
	
	private Logger LOGGER = Logger.getLogger(TinderBotAuthenticateUsers.class);
	
	@Autowired
	private ITinderService tinderService;
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Check all user's api key, it could be expired
	 */
	public void checkApiKeyAtStart() {
		LOGGER.info("Checking if user's api key on database are valid");
		List<User> list = userRepository.findAll();
		LOGGER.info("User(s): " + list.size());
		
		/*
		 * using the metadata api from tinder to test the api key
		 */
		list.forEach((user) -> {
			tinderService.getMetadata(user);
		});
	}
	
}
