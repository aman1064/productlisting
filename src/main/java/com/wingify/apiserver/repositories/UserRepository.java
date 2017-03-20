package com.wingify.apiserver.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wingify.apiserver.entities.User;


public interface UserRepository extends MongoRepository<User, String> {
	User findByUserNameAndPassword(String userName, String password);
}
