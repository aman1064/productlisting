package com.wingify.apiserver.entities;

public class AuthenticationResponseWrapper {
	private User user;
	private String token;

	public AuthenticationResponseWrapper(User user, String token) {
		if (user != null) {
			user.setPassword(null);
		}
		this.user = user;
		this.token = token;

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}