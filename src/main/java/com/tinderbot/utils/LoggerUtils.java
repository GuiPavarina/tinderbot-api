package com.tinderbot.utils;

import java.util.List;

import org.apache.log4j.Logger;

import com.tinderbot.entities.MatchUser;
import com.tinderbot.entities.Message;

public class LoggerUtils {
	
	private static Logger LOGGER = Logger.getLogger(LoggerUtils.class);

	/**
	 * Only called in debug mode
	 * @param list list to be printed, toString() will be called
	 * @param message message to be printed before the each element of the list
	 */
	public static void printList(List<?> list, String message) {
		for (Object o : list) {
			LOGGER.debug(message + o.toString());
		}
	}

}
