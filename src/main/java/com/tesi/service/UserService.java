package com.tesi.service;

import com.tesi.entity.User;

import java.util.UUID;

public interface UserService {
	User findUserById(UUID id);
	void saveUser(User user);
	void deleteUser(UUID id);
	User getUserByUsername(String username);
	boolean update(User updatedUser);
	User findByEmail(String email);
}
