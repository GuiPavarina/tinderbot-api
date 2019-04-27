package com.tinderbot.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "tinder.url")
public class TinderConfig {

	private String auth;

	private String anotherUserProfile;

	private String changeLocation;

	private String likeUser;

	private String matchFromId;

	private String messageFromId;

	private String metadata;

	private String passUser;

	private String recommendations;

	private String sendMessage;

	private String superLikeUser;

	private String updates;

	private String userProfile;

	public String getAnotherUserProfile() {
		return anotherUserProfile;
	}

	public String getAuth() {
		return auth;
	}

	public String getChangeLocation() {
		return changeLocation;
	}

	public String getLikeUser() {
		return likeUser;
	}

	public String getMatchFromId() {
		return matchFromId;
	}

	public String getMessageFromId() {
		return messageFromId;
	}

	public String getMetadata() {
		return metadata;
	}

	public String getPassUser() {
		return passUser;
	}

	public String getRecommendations() {
		return recommendations;
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public String getSuperLikeUser() {
		return superLikeUser;
	}

	public String getUpdates() {
		return updates;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setAnotherUserProfile(String anotherUserProfile) {
		this.anotherUserProfile = anotherUserProfile;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public void setChangeLocation(String changeLocation) {
		this.changeLocation = changeLocation;
	}

	public void setLikeUser(String likeUser) {
		this.likeUser = likeUser;
	}

	public void setMatchFromId(String matchFromId) {
		this.matchFromId = matchFromId;
	}

	public void setMessageFromId(String messageFromId) {
		this.messageFromId = messageFromId;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public void setPassUser(String passUser) {
		this.passUser = passUser;
	}

	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	public void setSuperLikeUser(String superLikeUser) {
		this.superLikeUser = superLikeUser;
	}

	public void setUpdates(String updates) {
		this.updates = updates;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

}
