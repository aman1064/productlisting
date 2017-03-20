package com.wingify.apiserver.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wingify.apiserver.entities.User;
import com.wingify.apiserver.repositories.UserRepository;


public class UserService implements UserDetailsService {
	
    private final HashMap<String, User> userMap = new HashMap<String, User>();
    @Autowired
    private UserRepository userRepository;
 
    @Override
    public final UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOne(username);
        if (user == null) {
            throw new RuntimeException("user not found");
        }
        return user;
    }
 
    public void addUser(User user) {
        userMap.put(user.getUserName(), user);
    }
}
