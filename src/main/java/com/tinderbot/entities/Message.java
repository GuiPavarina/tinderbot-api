package com.tinderbot.entities;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component("message")
public class Message {
	
	@Id
	private String id;

	private String matchId;

	private String from;

	private String to;
	
	private String message;
	
	public Message() {
		
	}
	
	public Message(String id, String matchId, String from, String to, String message) {
		this.id = id;
		this.matchId = matchId;
		this.from = from;
		this.to = to;
		this.message = message;
	}
	
	public String getFrom() {
		return from;
	}
	
	public String getId() {
		return id;
	}

	public String getMatchId() {
		return matchId;
	}

	public String getMessage() {
		return message;
	}

	public String getTo() {
		return to;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
	@Override
	public String toString() {
		return String.format(
                "Message[id='%s', matchId='%s', from='%s', to='%s', message='%s']",
                id, matchId, from, to, message);
    }
	
}
