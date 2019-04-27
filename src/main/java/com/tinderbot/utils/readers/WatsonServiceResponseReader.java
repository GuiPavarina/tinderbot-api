package com.tinderbot.utils.readers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class WatsonServiceResponseReader {
	
	private static Logger LOGGER = Logger.getLogger(WatsonServiceResponseReader.class);
	
	/**
	 * Function to extract message from WATSON's json.
	 * @param response
	 * @return String
	 */
	public static String getText(MessageResponse response) {
		
		try {
			JSONObject json = new JSONObject(response.toString());
			
			String str = new JSONObject(json.get("output").toString()).get("text").toString();
			str = str.replaceAll("^\\[|]$", "");
			str = str.replaceAll("\"", "");
			
			List<String> array = new ArrayList<String>(Arrays.asList(str.split(",")));
			
			return array.get(0).toString();
		} catch (JSONException e) {
			LOGGER.error(e.getMessage());
			return "";
		}
		
	}

}
