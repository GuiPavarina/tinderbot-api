package com.tinderbot.utils;

import java.time.Instant;

public class DateUtils {
	
	/**
	 * Returns date in ISO-8601 representation
	 * @return
	 */
	public static String dateNowToUTC() {
		return Instant.now().toString();
	}
	
}
