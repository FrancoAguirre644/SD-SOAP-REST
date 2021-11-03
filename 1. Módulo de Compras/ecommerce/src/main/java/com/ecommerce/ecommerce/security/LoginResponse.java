package com.ecommerce.ecommerce.security;

public class LoginResponse {

	private String token;
	private UserResponse user;
	
	public LoginResponse(String token, UserResponse user) {
		super();
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "LoginResponse [token=" + token + ", user=" + user + "]";
	}
}
