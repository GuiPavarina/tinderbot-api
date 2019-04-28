package com.tinderbot.resources;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tinderbot.configurations.security.JwtProvider;
import com.tinderbot.entities.User;
import com.tinderbot.entities.requests.AccessTokenRequest;
import com.tinderbot.entities.responses.ProfileResponse;
import com.tinderbot.repositories.MatchUserRepository;
import com.tinderbot.repositories.NotificationRepository;
import com.tinderbot.repositories.UserRepository;
import com.tinderbot.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1/profiles")
public class ProfileResource {
	
	private final static Logger LOGGER = Logger.getLogger(ProfileResource.class);
	
	@Autowired
	private MatchUserRepository matchUserRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@RequestMapping(value = "/info" , method = RequestMethod.GET)
	public ResponseEntity<ProfileResponse> getInfo(
			@RequestHeader(value="Authorization") String authorizationHeader
		){
		
        String username = jwtProvider.getUserNameFromJwtToken(authorizationHeader);
		
		User user = userRepository.findOneByUsername(username);
		
		long size = matchUserRepository.countByTinderId(user.getTinderId());
		int notificationSize = notificationRepository.findByUsername(username).getSize();
		
		return ResponseEntity.ok().body(new ProfileResponse(size, user.getImage(), notificationSize));
	}
	
	@RequestMapping(value = "/register/token",	method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> validToken(
			@RequestHeader(value="Authorization") String authorizationHeader,
			@RequestBody AccessTokenRequest facebookAccessToken
		){
		
		String username = jwtProvider.getUserNameFromJwtToken(authorizationHeader);
		
		User user = userRepository.findOneByUsername(username);
		
		boolean result = userService.authUser(user, facebookAccessToken.getToken());
		if(result) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(401).build();
		}
	}

}
