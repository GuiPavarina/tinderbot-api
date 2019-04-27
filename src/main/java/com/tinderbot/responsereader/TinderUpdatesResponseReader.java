package com.tinderbot.responsereader;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tinderbot.entities.MatchUser;
import com.tinderbot.entities.Message;

public class TinderUpdatesResponseReader {
	
	private static Logger LOGGER = Logger.getLogger(TinderUpdatesResponseReader.class);
	
	/**
	 * Read all matches information from Tinder's update
	 * @param json
	 * @return
	 */
	public static List<MatchUser> readAllMatches(String tinderId, JSONObject json){
		List<MatchUser> list = new ArrayList<MatchUser>();
		
		try {
			JSONArray jsonArray = new JSONArray(json.get("matches").toString());
			
			JSONObject jsonAux;			
			for(int index = 0 ; index < jsonArray.length(); index++) {
				jsonAux = jsonArray.getJSONObject(index);

				if(jsonAux.has("person")) {
					String matchId = jsonAux.get("_id").toString();
					boolean isSuperLike = jsonAux.optBoolean("is_super_like");
					List<Message> messages = toMessage(jsonAux.optJSONArray("messages"));
					
					jsonAux = jsonAux.getJSONObject("person");
					
					String name = jsonAux.optString("name");
					String bio = jsonAux.optString("bio");
					String birthDate = jsonAux.optString("birth_date");
					String userId = jsonAux.get("_id").toString();
					List<String> images = toImg(jsonAux.optJSONArray("photos"));
					
					boolean isTalking = false;
					if(messages.size() > 0)
						isTalking = true;
					
					list.add(new MatchUser(tinderId, userId, matchId, name, bio, isSuperLike, messages, images , false, isTalking, birthDate));
				}
			}
			
			return list;
		} catch (JSONException e) {
			LOGGER.error(e.getMessage());
			return list;
		}
	
		
	}
	
	public static List<Message> readAllMessages(JSONObject json){
		List<Message> list = new ArrayList<Message>();
		
		try {
			
			JSONArray jsonArray = new JSONArray(json.opt("matches").toString());
			JSONObject jsonAux;
			for(int index = 0 ; index < jsonArray.length() ; index++) {
				jsonAux = jsonArray.getJSONObject(index);
				
				if(!jsonAux.has("person")) {
					JSONArray messages = new JSONArray(jsonAux.opt("messages").toString());
					
					for(Message message : toMessage(messages)) {
						list.add(message);
					}
					
				}
				
			}
			
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		
		return list;
	}
	
	/**
	 * Convert an array of jsons to List of Message
	 * @param JSONArray
	 * @return
	 */
	private static List<Message> toMessage(JSONArray json){
			
		List<Message> list = new ArrayList<Message>();
		
		try {		
			JSONObject jsonObject;
			for(int index = 0 ; index < json.length() ; index ++) {
				jsonObject = json.getJSONObject(index);
				String matchId = jsonObject.optString("match_id");
				String from = jsonObject.optString("from");
				String id = jsonObject.optString("_id");
				String to = jsonObject.optString("to");
				String message = jsonObject.optString("message").replaceAll("\n", " ");
				list.add(new Message(id, matchId, from, to, message));
			}
		} catch (JSONException e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}
	
	private static List<String> toImg(JSONArray json) {
		List<String> images = new ArrayList<String>(); 
		
		for(int i = 0 ; i < json.length() ; i++) {
			images.add(json.optJSONObject(i).optJSONArray("processedFiles").optJSONObject(0).optString("url"));
		}
		
		return images;
		
	}
}
