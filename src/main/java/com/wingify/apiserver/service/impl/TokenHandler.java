package com.wingify.apiserver.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public final class TokenHandler {

	private final String secret;
	private final UserService userService;
	private static final String SECRET_SIGNING_KEY = "study-platform";

	public TokenHandler(String secret, UserService userService) {
		this.secret = secret;
		this.userService = userService;
	}

	public UserDetails parseUserFromToken(String token) {
		String username = Jwts.parser().setSigningKey(SECRET_SIGNING_KEY).parseClaimsJws(token).getBody().getSubject();
		return userService.loadUserByUsername(username);
	}

	public String createTokenForUser(UserDetails user, HttpServletRequest request) {
		Date date = new Date();
		LocalDateTime time = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		time = time.plusDays(7);
		Date expDate = Date.from(time.toInstant(ZoneOffset.UTC));
		JwtBuilder builder = Jwts.builder().setSubject(user.getUsername()).signWith(SignatureAlgorithm.HS256, secret)
				.setExpiration(expDate);
		return builder.compact();
	}
}
