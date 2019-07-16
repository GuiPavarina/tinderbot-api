package com.tinderbot.utils;

import java.util.List;

import org.apache.log4j.Logger;

import com.tinderbot.entities.MatchUser;
import com.tinderbot.entities.Message;

public class LoggerUtils {
	
	private static Logger LOGGER = Logger.getLogger(LoggerUtils.class);
	
	public static void printListOfMessages(List<Message> list) {
		for(Message message : list ) {
			LOGGER.debug("Adding message: " + message.toString());
		}
	}
	
	public static void printListOfMatchUsers(List<MatchUser> list) {
		for(MatchUser matchUser : list ) {
			LOGGER.debug("Adding User: " + matchUser.toString());
		}
	}

}
