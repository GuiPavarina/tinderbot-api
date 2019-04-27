package com.tinderbot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tinderbot.entities.MatchUser;

@Repository("matchUserRepository")
public interface MatchUserRepository extends MongoRepository<MatchUser, String> {
	
	Boolean existsByTinderIdAndUserId(String tinderId, String userId);
	
	MatchUser findByTinderIdAndUserId(String tinderId, String userId);
	
	MatchUser findByMatchId(String matchId);
	
	Long countByTinderId(String tinderId);
	
	List<MatchUser> findByTinderId(String tinderId);
	
	Page<MatchUser> findByTinderId(String tinderId, Pageable page);
	
}
