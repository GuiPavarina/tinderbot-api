package com.tinderbot.services;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinderbot.entities.User;
import com.tinderbot.services.tinder.ITinderService;
import com.tinderbot.services.tinder.TinderAnotherUserProfileServiceImpl;
import com.tinderbot.services.tinder.TinderChangeLocationServiceImpl;
import com.tinderbot.services.tinder.TinderLikeUserServiceImpl;
import com.tinderbot.services.tinder.TinderMatchFromIdServiceImpl;
import com.tinderbot.services.tinder.TinderMessageFromIdServiceImpl;
import com.tinderbot.services.tinder.TinderMetadataServiceImpl;
import com.tinderbot.services.tinder.TinderPassUserServiceImpl;
import com.tinderbot.services.tinder.TinderRecommendationsServiceImpl;
import com.tinderbot.services.tinder.TinderSendMessageServiceImpl;
import com.tinderbot.services.tinder.TinderSuperLikeUserServiceImpl;
import com.tinderbot.services.tinder.TinderUpdatesServiceImpl;
import com.tinderbot.services.tinder.TinderUserProfileServiceImpl;

@Service("tinderService")
public class TinderServiceImpl implements ITinderService {

	@Autowired
	private TinderAnotherUserProfileServiceImpl tinderAnotherUserProfileServiceImpl;
	
	@Autowired
	private TinderChangeLocationServiceImpl tinderChangeLocationServiceImpl;
	
	@Autowired
	private TinderLikeUserServiceImpl tinderLikeUserServiceImpl;
	
	@Autowired
	private TinderMatchFromIdServiceImpl tinderMatchFromIdServiceImpl;
	
	@Autowired
	private TinderMessageFromIdServiceImpl tinderMessageFromIdServiceImpl;
	
	@Autowired
	private TinderMetadataServiceImpl tinderMetadataServiceImpl;
	
	@Autowired
	private TinderPassUserServiceImpl tinderPassUserServiceImpl;
	
	@Autowired
	private TinderRecommendationsServiceImpl tinderRecommendationsServiceImpl;
	
	@Autowired
	private TinderSendMessageServiceImpl tinderSendMessageServiceImpl;
	
	@Autowired
	private TinderSuperLikeUserServiceImpl tinderSuperLikeUserServiceImpl;
	
	@Autowired
	private TinderUpdatesServiceImpl tinderUpdatesServiceImpl;
	
	@Autowired
	private TinderUserProfileServiceImpl tinderUserProfileServiceImpl;
	
	@Override
	public JSONObject getAnotherUserProfile(User user,String id) {
		return tinderAnotherUserProfileServiceImpl.execute(user,id);
	}

	@Override
	public JSONObject changeLocation(User user,String lat, String lon) {
		return tinderChangeLocationServiceImpl.execute(user,lat, lon);
	}

	@Override
	public JSONObject likeUser(User user,String id) {
		return tinderLikeUserServiceImpl.execute(user,id);
	}

	@Override
	public JSONObject getMatchFromId(User user,String id) {
		return tinderMatchFromIdServiceImpl.execute(user,id);
	}

	@Override
	public JSONObject getMessageFromId(User user,String id) {
		return tinderMessageFromIdServiceImpl.execute(user,id);
	}

	@Override
	public JSONObject getMetadata(User user) {
		return tinderMetadataServiceImpl.execute(user);
	}

	@Override
	public JSONObject passUser(User user,String id) {
		return tinderPassUserServiceImpl.execute(user,id);
	}

	@Override
	public JSONObject getRecommendations(User user) {
		return tinderRecommendationsServiceImpl.execute(user);
	}

	@Override
	public JSONObject sendMessage(User user,String message, String id) {
		return tinderSendMessageServiceImpl.execute(user,message, id);
	}

	@Override
	public JSONObject superLikeUser(User user,String id) {
		return tinderSuperLikeUserServiceImpl.execute(user,id);
	}
	
	@Override
	public JSONObject getUpdatesFromDate(User user,String dateTime) {
		return tinderUpdatesServiceImpl.execute(user,dateTime);
	}
	
	@Override
	public JSONObject getUserProfile(User user) {
		return tinderUserProfileServiceImpl.execute(user);
	}



}
