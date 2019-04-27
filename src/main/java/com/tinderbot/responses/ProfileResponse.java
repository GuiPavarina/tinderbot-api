package com.tinderbot.responses;

public class ProfileResponse {
	
	private Long numberOfMatches;
	
	private String userImage;
	
	private int news;
	
	public ProfileResponse() {
		
	}
	
	public ProfileResponse(Long numberOfMatches, String userImage, int news) {
		this.numberOfMatches = numberOfMatches;
		this.userImage = userImage;
		this.news = news;
	}

	public Long getNumberOfMatches() {
		return numberOfMatches;
	}

	public void setNumberOfMatches(Long numberOfMatches) {
		this.numberOfMatches = numberOfMatches;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public int getNews() {
		return news;
	}

	public void setNews(int news) {
		this.news = news;
	}

	@Override
	public String toString() {
		return String.format("ProfileResponse [numberOfMatches=%s, userImage=%s, news=%s]", numberOfMatches, userImage, news);
	}
	
}
