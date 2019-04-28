package com.tinderbot.entities.requests;

public class IdRequest {

	private String id;
	
	public IdRequest() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Id [id=" + id + "]";
	}
	
}
