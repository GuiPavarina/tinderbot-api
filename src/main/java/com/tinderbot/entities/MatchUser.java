package com.tinderbot.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component("matchUser")
public class MatchUser {

	@Id
	private String id;
	
	private String tinderId;
	
	private String userId;

	private String matchId;

	private String name;
	
	private String bio;
	
	private String birthDate;
	
	private List<Message> messages;
	
	private boolean isSuperLike;
	
	private boolean block;
	
	private boolean isTalking;
	
	private List<String> images;
	
	public MatchUser() {
		
	}
	
	public MatchUser(String tinderId, String userId, String matchId, String name, String bio, boolean isSuperLike, List<Message> messages,List<String> images, boolean block, boolean isTalking, String birthDate) {
		this.setTinderId(tinderId);
		this.userId = userId;
		this.matchId = matchId;
		this.name = name;
		this.bio = bio;
		this.isSuperLike = isSuperLike;
		this.messages = messages;
		this.images = images;
		this.setBlock(block);
		this.isTalking = isTalking;
		this.birthDate = birthDate;
	}

	public String getBio() {
		return bio;
	}
	
	public String getBirthDate() {
		return birthDate;
	}

	public String getId() {
		return id;
	}

	public List<String> getImages() {
		return images;
	}

	public String getMatchId() {
		return matchId;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public String getName() {
		return name;
	}

	public String getTinderId() {
		return tinderId;
	}

	public String getUserId() {
		return userId;
	}

	public boolean isBlock() {
		return block;
	}

	public boolean isSuperLike() {
		return isSuperLike;
	}

	public boolean isTalking() {
		return isTalking;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setImage(List<String> images) {
		this.images = images;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSuperLike(boolean isSuperLike) {
		this.isSuperLike = isSuperLike;
	}

	public void setTalking(boolean isTalking) {
		this.isTalking = isTalking;
	}

	public void setTinderId(String tinderId) {
		this.tinderId = tinderId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		 return String.format(
	                "MatchUser[tinderId='%s', id='%s', userId='%s', matchId='%s', name='%s', bio='%s', isSuperLike='%s', messages='%s',images='%s', block='%s', isTalking='%s', birthDate='%s']",
	                tinderId, id, userId, matchId, name, bio, isSuperLike, messages,images, block, isTalking, birthDate);
	    }
	
}
