package com.example.tesi.service;

import com.example.tesi.entity.User;
import com.example.tesi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User getUser(UUID userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(UUID id) {
		userRepository.deleteById(id);
	}
}
