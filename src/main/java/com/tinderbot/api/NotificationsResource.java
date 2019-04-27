package com.tinderbot.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tinderbot.config.security.JwtProvider;
import com.tinderbot.entities.Notifications;
import com.tinderbot.repository.NotificationRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/notifications")
public class NotificationsResource {

	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Notifications> getNews(HttpServletRequest request){
		
		String header = request.getHeader("Authorization");
        String username = jwtProvider.getUserNameFromJwtToken(header);
		
		Notifications notifications = notificationRepository.findByUsername(username);
		
		return ResponseEntity.ok(notifications);
	}
	
}
