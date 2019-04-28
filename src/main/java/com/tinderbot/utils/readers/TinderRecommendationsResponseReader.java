package com.tinderbot.utils.readers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TinderRecommendationsResponseReader {

	public static List<String> getAllUsersId(JSONObject json) throws JSONException{
		
		List<String> list = new ArrayList<String>();
		JSONArray jsonArray = new JSONArray(json.get("results").toString());
		
		JSONObject jsonAux;
		for(int index = 0 ; index < jsonArray.length(); index++) {
			jsonAux = jsonArray.getJSONObject(index);
			list.add(jsonAux.get("_id").toString());
		}
		
		return list;
	}
	
}
