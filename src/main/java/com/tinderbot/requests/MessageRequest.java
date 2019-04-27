package com.tinderbot.requests;

public class MessageRequest {
	
	private String id;
	
	private String message;

	public MessageRequest() {
		
	}

	public MessageRequest(String id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return String.format("MessageRequest [id=%s, message=%s]", id, message);
	}

}
