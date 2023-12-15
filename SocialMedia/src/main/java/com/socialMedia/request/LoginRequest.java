package com.socialMedia.request;

public class LoginRequest {
	
	//this class will have Email and Password as request 
	
	private String email;
	private String password;
	
	public LoginRequest() {
		// TODO Auto-generated constructor stub
	}

	public LoginRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
