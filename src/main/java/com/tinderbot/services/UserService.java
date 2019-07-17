package com.tinderbot.services;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinderbot.entities.User;
import com.tinderbot.repositories.UserRepository;
import com.tinderbot.services.tinder.ITinderService;
import com.tinderbot.services.tinder.TinderAuthenticationServiceImpl;
import com.tinderbot.utils.DateUtils;
import com.tinderbot.utils.TinderUtils;

@Service("userService")
public class UserService {
	
	private static Logger LOGGER = Logger.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TinderAuthenticationServiceImpl tinderAuthenticationServiceImpl;
	
	@Autowired
	private ITinderService tinderService;
	
	/**
	 * Returns all users authenticated on Tinder
	 * @return list<User>
	 */
	public List<User> getAllAuthenticated(){
		return userRepository.findAll()
				.stream()
				.filter(User::isAuthenticated)
				.collect(Collectors.toList());
	}
	
	/**
	 * Authenticate an user using facebook access token and id
	 * @param user user
	 * @param facebookAccessToken facebook access token
	 * @return boolean
	 */
	public boolean authUser(User user, String facebookAccessToken) {
		
		if(user.isAuthenticated())
			return true;
		
		String token = TinderUtils.getTokenFromJson(tinderAuthenticationServiceImpl.execute(facebookAccessToken));
		if(token.isEmpty()) {

			//TODO
			// - Better log message using names in future
			LOGGER.info("User: " + user.getUsername() + " is not authenticated");
			
			setAuthenticated(user,false);
			
			return false;
		}
		LOGGER.info("User: " + user.getUsername() + "is authenticated");
		user.setTinderToken(token);
		
		String tinderId = tinderService.getMetadata(user).optJSONObject("user").optString("_id");
		
		String image = tinderService.getUserProfile(user).optJSONArray("photos").optJSONObject(0).optJSONArray("processedFiles").optJSONObject(0).optString("url");
		
		user.setImage(image);
		user.setTinderId(tinderId);
	
		setAuthenticated(user,true);
		
		return true;
	}
	
	/**
	 * Set user param ( isAuthenticated ) in database
	 * @param user
	 * @param auth
	 */
	public void setAuthenticated(User user,boolean auth) {
		user.setAuthenticated(auth);
		userRepository.save(user);
	}
	
	/**
	 * Gets and updates the User's Last Activity date in ISO-8601 representation
	 * @return String ISO-8601 Date Format
	 */
	public String getAndUpdateLastActivity(User user) {
		
		String lastActivity = user.getLastActivity();
		
		LOGGER.debug("Getting Last Activity: " + lastActivity + " , user id:" + user.getId());
		
		setLastActivity(user);

		return lastActivity;
	}
	
	/**
	 * Gets last Activity without updating
	 * @return String ISO-8601 Date Format
	 */
	public String getLastActivity(User user) {
		return userRepository
				.findOneById(user.getId())
				.getLastActivity();
	}
	
	/**
	 * Set or create the Last Activity date using user's tinder id
	 */
	private void setLastActivity(User user) {
	
		String lastActivity = DateUtils.dateNowToUTC();
		
		user.setLastActivity(lastActivity);
		
		LOGGER.debug("Last Activity setted to: " + lastActivity + " , user id: " + user.getId());
		userRepository.save(user);
				
	}
	
}
