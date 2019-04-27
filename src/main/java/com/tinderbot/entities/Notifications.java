package com.tinderbot.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Notifications {

	@Id
	private String id;
	
	private String username;
	
	private List<Message> messages;
	
	private int size;
	
	public Notifications(){
		messages = new ArrayList<Message>();
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public void addMessage(Message message) {
		messages.add(message);
	}
	
	public void addAllMessages(List<Message> messages) {
		this.messages.addAll(messages);
		size = this.messages.size();
	}
	
	public void clearNews() {
		messages.clear();
		size = 0;
	}

	public int getSize() {
		return size;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Notifications [id=" + id + ", username=" + username + ", messages=" + messages + ", size=" + size + "]";
	}

	
}
