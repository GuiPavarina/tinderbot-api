package com.tinderbot.tinderservices;

import org.json.JSONObject;

import com.tinderbot.entities.User;

public interface ITinderService {
	
	/**
	 * Gett all information available about an specific user
	 * @param id
	 * @return JSONObject
	 */
	JSONObject getAnotherUserProfile(User user,String id);
	
	/**
	 * Change user's location using lat and lon as parameters
	 * @param lat
	 * @param lon
	 * @return JSONObject
	 */
	JSONObject changeLocation(User user,String lat, String lon);
	
	/**
	 * Like user by user's id. It will return if match was positive or false and 
	 * if available it will return match_id  
	 * @param id
	 * @return JSONObject
	 */
	JSONObject likeUser(User user,String id);
	
	/**
	 * Get all match information from user's id.
	 * @param id
	 * @return JSONObject
	 */
	JSONObject getMatchFromId(User user,String id);
	
	/**
	 * Returns all messages from a match.
	 * @param id
	 * @return JSONObject
	 */
	JSONObject getMessageFromId(User user,String id);
	
	/**
	 * Returns user's metadata.
	 * @return JSONObject
	 */
	JSONObject getMetadata(User user);
	
	/**
	 * Pass user by its id.
	 * @param id
	 * @return JSONObject
	 */
	JSONObject passUser(User user,String id);
	
	/**
	 * Get a array of people recommended by Tinder's API.
	 * @return JSONObject
	 */
	JSONObject getRecommendations(User user);
	
	/**
	 * Send message to someone using match id.
	 * @param message
	 * @param id (match id)
	 * @return JSONObject
	 */
	JSONObject sendMessage(User user,String message, String id);
	
	/**
	 * Super like user by its id.
	 * @param id
	 * @return JSONObject
	 */
	JSONObject superLikeUser(User user,String id);
		
	/**
	 * Get all updates from a specific date.
	 * The string must be in ISO-8601 representation;
	 * @param dateTime (YYYY-mm-DDTHH:MM:SS.MMMZ)
	 * @return JSONObject
	 */
	JSONObject getUpdatesFromDate(User user,String dateTime);
	
	/**
	 * Returns all user information
	 * @return JSONObject
	 */
	JSONObject getUserProfile(User user);

}
