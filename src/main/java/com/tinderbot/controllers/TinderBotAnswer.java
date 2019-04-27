package com.tinderbot.controllers;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tinderbot.entities.NewMessages;
import com.tinderbot.entities.User;
import com.tinderbot.repositories.MatchUserRepository;
import com.tinderbot.services.TinderServiceImpl;
import com.tinderbot.services.watson.WatsonServiceImpl;

@Controller("tinderBotAnswer")
public class TinderBotAnswer {
	
	private static Logger LOGGER = Logger.getLogger(TinderBotAnswer.class);
	
	@Autowired
	private NewMessages newMessages;
	
	@Autowired
	private WatsonServiceImpl watsonServiceImpl;
	
	@Autowired
	private TinderServiceImpl tinderServiceImpl;
	
	@Autowired
	private MatchUserRepository matchUserRepository;
	
	public void checkNewMessages(User user) {
		LOGGER.info("Checking new messages");
		
		if(!newMessages.getMap().isEmpty()) {
			LOGGER.info("New Messages: " + newMessages.toString());
			
			for (Map.Entry<String, String> entry : newMessages.getMap().entrySet()) {
			    String id = entry.getKey();
			    String value = entry.getValue();
			    
			    if(matchUserRepository.findByMatchId(id).isBlock()) {
			    	LOGGER.info("Match ID: " + id + " is blocked");
			    } else {
			    	String response = watsonServiceImpl.sendMessage(value,id);
			    	LOGGER.info("--> id: " + id);
				    LOGGER.info("Sending message: " + response + " to: " + id);
				    tinderServiceImpl.sendMessage(user,response, id);
			    }
			    			    
			}
			
			newMessages.clear();
		}
	}

}
