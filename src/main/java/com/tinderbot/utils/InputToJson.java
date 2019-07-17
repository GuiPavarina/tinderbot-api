package com.tinderbot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

public class InputToJson {
	
	/**
	 * Convert InputStreamReader to JSONObject
	 * @param input
	 * @return JSONObject
	 * @throws IOException
	 */
	public static JSONObject execute(InputStreamReader input) throws IOException {
		
		StringBuilder inputLine = new StringBuilder();
		
		BufferedReader bf = new BufferedReader(input);
		
		String tmp;
		
		while ((tmp = bf.readLine()) != null) {
			inputLine.append(tmp);
		}

		input.close();

		JSONObject json;
		try {
			json = new JSONObject(inputLine.toString());
			return json;
		} catch (JSONException e) {
			e.printStackTrace();
			return new JSONObject();
		}
	}

}
