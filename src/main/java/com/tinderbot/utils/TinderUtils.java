package com.tinderbot.utils;

import org.json.JSONObject;

public class TinderUtils {
	
	/**
	 * Get token from authentication json.
	 * @param json
	 * @return
	 */
	public static String getTokenFromJson(JSONObject json) {
		return json.optString("token");
	}

}
