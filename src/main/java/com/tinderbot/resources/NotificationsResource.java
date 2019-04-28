package com.tinderbot.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tinderbot.configurations.security.JwtProvider;
import com.tinderbot.entities.Notifications;
import com.tinderbot.repositories.NotificationRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/notifications")
public class NotificationsResource {

	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Notifications> getNews(
			@RequestHeader(value="Authorization") String authorizationHeader
		){
		
        String username = jwtProvider.getUserNameFromJwtToken(authorizationHeader);
		
		Notifications notifications = notificationRepository.findByUsername(username);
		
		return ResponseEntity.ok(notifications);
	}
	
}
