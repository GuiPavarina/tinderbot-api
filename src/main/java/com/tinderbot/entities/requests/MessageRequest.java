package com.tinderbot.entities.requests;

public class MessageRequest {
	
	private String matchId;
	
	private String message;

	public MessageRequest() {
		
	}

	public MessageRequest(String matchId, String message) {
		super();
		this.matchId = matchId;
		this.message = message;
	}

	public String getMatchId() {
		return matchId;
	}

	public String getMessage() {
		return message;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return String.format("MessageRequest [matchId=%s, message=%s]", matchId, message);
	}

}
