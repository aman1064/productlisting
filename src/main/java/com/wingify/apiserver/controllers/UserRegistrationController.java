package com.wingify.apiserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wingify.apiserver.entities.GenericResponse;
import com.wingify.apiserver.entities.User;
import com.wingify.apiserver.service.UserRegistrationService;


@CrossOrigin
@RestController
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@RequestMapping(value = "/api/user/save", method = RequestMethod.POST)
	public GenericResponse<User> registerUser(@RequestBody User user) {
		User savedUser = userRegistrationService.saveUser(user);
		savedUser.setPassword(null);
		return new GenericResponse<>(savedUser);
	}


	@RequestMapping(value = "/api/user/available", method = RequestMethod.GET)
	public GenericResponse<Boolean> checkIfUserNameExists(@RequestParam(value = "userName") String userName) {
		return new GenericResponse<>(!userRegistrationService.userExists(userName));
	}

	
	@RequestMapping(value = "/api/userDetails", method = RequestMethod.GET)
	public GenericResponse<User> getUserByUserName(@RequestParam(value = "userName") String userName) {
		return new GenericResponse<>(userRegistrationService.getUser(userName));
	}

}
