package com.ecommerce.ecommerce.security;

import com.ecommerce.ecommerce.entities.User;

public class LoginResponse {

	private String token;
	private User user;
	
	public LoginResponse(String token, User user) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "LoginResponse [token=" + token + ", user=" + user + "]";
	}
}
