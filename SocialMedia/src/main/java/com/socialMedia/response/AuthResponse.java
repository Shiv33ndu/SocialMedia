package com.socialMedia.response;

// response classes are basically for the DataStrcture purposes, will be used to create a response medium b/w front
// and Backend Side, they doesnt come under Spring Application Context so, not necessarily needed to annotate them as 
// any @Component or anything...

public class AuthResponse {
	
	private String token;
	private String message;
	
	public AuthResponse() {}
	
	public AuthResponse(String token, String message) {
		super();
		this.token = token;
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
