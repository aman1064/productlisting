package com.wingify.apiserver.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.wingify.apiserver.entities.UserAuthentication;



@Service
public class TokenAuthenticationService {
	private static final String AUTH_HEADER_NAME = "T";
	private static final String SECRET_SIGNING_KEY = "study-platform";
	private final TokenHandler tokenHandler;

	public TokenAuthenticationService() {
		this.tokenHandler = new TokenHandler(SECRET_SIGNING_KEY, new UserService());
	}

	public TokenAuthenticationService(String secret, UserService userService) {
		tokenHandler = new TokenHandler(secret, userService);
	}

	public void addAuthentication(HttpServletResponse response, HttpServletRequest request,
			com.wingify.apiserver.entities.UserAuthentication authentication) {
		final UserDetails user = authentication.getDetails();
		response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user, request));
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token != null) {
			final UserDetails user = tokenHandler.parseUserFromToken(token);
			if (user != null) {
				return new UserAuthentication(user);
			}
		}
		return null;
	}
}
