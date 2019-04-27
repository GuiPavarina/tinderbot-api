package com.tinderbot.entities;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component("user")
public class User {
	
	@Id
	private String id;
	
	private String username;
	
	private String password;
	
	private String email;

	private String tinderToken = "";

	private String tinderId = "";
	
	private boolean isAuthenticated = false;
	
	private String lastActivity = "";
	
	private String image = "";
	
	public User() {	}
	
	public User(String username, String email, String password) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public String getImage() {
		return image;
	}

	public String getLastActivity() {
		return lastActivity;
	}

	public String getTinderId() {
		return tinderId;
	}

	public String getTinderToken() {
		return tinderToken;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setLastActivity(String lastActivity) {
		this.lastActivity = lastActivity;
	}

	public void setTinderId(String tinderId) {
		this.tinderId = tinderId;
	}

	public void setTinderToken(String tinderToken) {
		this.tinderToken = tinderToken;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", tinderToken=" + tinderToken + ", tinderId=" + tinderId + ", isAuthenticated="
				+ isAuthenticated + ", lastActivity=" + lastActivity + ", image=" + image + "]";
	}

}
