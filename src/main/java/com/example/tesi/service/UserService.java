package com.example.tesi.service;

import com.example.tesi.entity.User;

import java.util.UUID;

public interface UserService {
	public User getUser(UUID id);
	public void saveUser(User user);
	public void deleteUser(UUID id);
}
