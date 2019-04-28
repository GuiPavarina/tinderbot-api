package com.tinderbot.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tinderbot.entities.User;

@Repository("userRepository")
public interface UserRepository extends MongoRepository<User, String> {
	
	User findOneById(String id);
	
	User findOneByUsername(String username);
	
	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
}
