package com.tinderbot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinderbot.entities.Message;
import com.tinderbot.entities.Notifications;
import com.tinderbot.entities.User;
import com.tinderbot.repository.NotificationRepository;

@Service("notificationsServiceImpl")
public class NotificationsServiceImpl {

	@Autowired
	private NotificationRepository notificationRepository;
	
	private Notifications createNotificationsForUser(String username) {
		Notifications notifications = new Notifications();
		notifications.setUsername(username);
		return notifications;
	}
	
	public void addNotifications(User user, List<Message> messages) {
		Notifications notifications = notificationRepository.findByUsername(user.getUsername());
		
		if(notifications == null) {
			notifications = this.createNotificationsForUser(user.getUsername());
		}
		
		notifications.addAllMessages(messages);
		notificationRepository.save(notifications);
	}
	
}
