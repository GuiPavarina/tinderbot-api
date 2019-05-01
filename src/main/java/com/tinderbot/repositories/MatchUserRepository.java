package com.tinderbot.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tinderbot.entities.MatchUser;

@Repository("matchUserRepository")
public interface MatchUserRepository extends MongoRepository<MatchUser, String> {
	
	Boolean existsByTinderIdAndMatchId(String tinderId, String matchId);
	
	MatchUser findByTinderIdAndUserId(String tinderId, String userId);
	
	MatchUser findByMatchId(String matchId);
	
	Long countByTinderId(String tinderId);
	
	List<MatchUser> findByTinderId(String tinderId);
	
	@Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
	List<MatchUser> findByNameAndUserId(String name, String userId);
	
	Page<MatchUser> findByTinderId(String tinderId, Pageable page);
	
}
