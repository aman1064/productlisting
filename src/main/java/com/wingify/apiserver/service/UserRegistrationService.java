package com.wingify.apiserver.service;

import com.wingify.apiserver.entities.User;

public interface UserRegistrationService {

	public User saveUser(User user);
	public Boolean userExists(String userName);
	public User getUser(String userName);
	
}
