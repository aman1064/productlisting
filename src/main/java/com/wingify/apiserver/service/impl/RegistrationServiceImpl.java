package com.wingify.apiserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wingify.apiserver.entities.User;
import com.wingify.apiserver.repositories.UserRepository;
import com.wingify.apiserver.service.UserRegistrationService;

@Service
public class RegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User saveUser(User user) {
		return userRepo.save(user);

	}

	@Override
	public Boolean userExists(String userName) {
		return userRepo.exists(userName);
	}

	@Override
	public User getUser(String userName) {
		return userRepo.findOne(userName);
	}

}
