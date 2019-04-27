package com.tinderbot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tinderbot.entities.Notifications;

@Repository("notificationRepository")
public interface NotificationRepository extends MongoRepository<Notifications, String>{

	Notifications findByUsername(String username);
	
}
