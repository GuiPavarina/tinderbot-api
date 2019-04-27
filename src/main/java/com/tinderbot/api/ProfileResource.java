package com.tinderbot.api;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tinderbot.config.security.JwtProvider;
import com.tinderbot.entities.Notifications;
import com.tinderbot.entities.User;
import com.tinderbot.repository.MatchUserRepository;
import com.tinderbot.repository.NotificationRepository;
import com.tinderbot.repository.UserRepository;
import com.tinderbot.requests.FacebookAccessToken;
import com.tinderbot.responses.ProfileResponse;
import com.tinderbot.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/profile")
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
	public ResponseEntity<ProfileResponse> getInfo(HttpServletRequest request){
		
		String header = request.getHeader("Authorization");
        String username = jwtProvider.getUserNameFromJwtToken(header);
		
		User user = userRepository.findOneByUsername(username);
		
		long size = matchUserRepository.countByTinderId(user.getTinderId());
		int notificationSize = notificationRepository.findByUsername(username).getSize();
		
		return ResponseEntity.ok().body(new ProfileResponse(size, user.getImage(), notificationSize));
	}
	
	@RequestMapping(value = "/register/token",	method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> validToken(HttpServletRequest request, @RequestBody FacebookAccessToken facebookAccessToken){
		
		String header = request.getHeader("Authorization");
		String username = jwtProvider.getUserNameFromJwtToken(header);
		
		User user = userRepository.findOneByUsername(username);
		
		boolean result = userService.authUser(user, facebookAccessToken.getToken());
		if(result) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(401).build();
		}
	}

}
