package com.wingify.apiserver.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wingify.apiserver.entities.AuthenticationResponseWrapper;
import com.wingify.apiserver.entities.GenericResponse;
import com.wingify.apiserver.entities.User;
import com.wingify.apiserver.entities.UserAuthentication;
import com.wingify.apiserver.repositories.UserRepository;
import com.wingify.apiserver.service.impl.TokenAuthenticationService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController

public class LoginLogoutController {

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	@Autowired
	private UserRepository userRepository;

	@ApiOperation(value="Used to issue header to the requesting client. The header will be set in Authorization Header name 'T' which needs to be passed in each request that needs authentication")
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public GenericResponse<AuthenticationResponseWrapper> issueToken(@RequestParam("userName") String userName,
			@RequestParam(value = "password") String password, HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Content-Type", "text/html");
		User user = userRepository.findByUserNameAndPassword(userName, password);
		if (user != null) {
			UserAuthentication authentication = new UserAuthentication(user);
			authentication.setAuthenticated(true);
			tokenAuthenticationService.addAuthentication(response, request, authentication);
		} else
			throw new SecurityException("Invalid login Credentials");

		return new GenericResponse<AuthenticationResponseWrapper>(
				new AuthenticationResponseWrapper(user, response.getHeader("T").toString()));
	}

	//@RequestMapping(value = "/api/logout", method = RequestMethod.POST)
	public GenericResponse<Boolean> logOut(HttpServletRequest request, HttpServletResponse response) {
		eraseCookie(request, response);
		return new GenericResponse<>(true);
	}

	private void eraseCookie(HttpServletRequest req, HttpServletResponse resp) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null)
			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setValue("");
				cookies[i].setPath("/");
				cookies[i].setMaxAge(0);
				resp.addCookie(cookies[i]);
			}
	}

}
