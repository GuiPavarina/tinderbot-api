package com.tinderbot.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.tinderbot.entities.User;
import com.tinderbot.services.TinderBotUpdateServiceImpl;
import com.tinderbot.services.UserService;

@Controller("tinderBot")
public class TinderBot {
	
	private final static Logger LOGGER = Logger.getLogger(TinderBot.class);
	
	@Autowired
	private TinderBotUpdateServiceImpl tinderBotUpdateServiceImpl;
	
	@Autowired
	private TinderBotAnswer tinderBotAnswer;
	
	@Autowired
	private UserService userService;
	
	@Scheduled(initialDelay = 45000, fixedRate = 45000)
	public void checkUpdates() {
		List<User> users = userService.getAllAuthenticated();
		
		users.forEach((user) -> {
			if (user.getLastActivity().isEmpty())
				fillInformation(user);
			else
				updateInformation(user);
			
			tinderBotAnswer.checkNewMessages(user);
		});
		
		LOGGER.info("Number of users authenticated: " + users.size());
	}
	
	private void fillInformation(User user) {
		LOGGER.info("Filling all information");
		tinderBotUpdateServiceImpl.updateFromStart(user);
	}
	
	private void updateInformation(User user) {
		LOGGER.info("Updating database from Last Activity");
		tinderBotUpdateServiceImpl.updateFromLastActivity(user);
	}

}
