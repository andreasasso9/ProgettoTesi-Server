package com.tesi.service;

import com.tesi.entity.User;
import com.tesi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
	public User findUserById(UUID userId) {
		Optional<User> user=userRepository.findById(userId);
		return user.orElse(null);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(UUID id) {
		userRepository.deleteById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean update(User updatedUser) {
		User olduser=userRepository.findByIdForUpdate(updatedUser.getId());
		if (olduser != null) {
			olduser.update(updatedUser);
			userRepository.save(olduser);
			return true;
		}

		return false;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public String findFoto(String username) {
		return Arrays.toString(userRepository.findFotoByUsername(username));
	}


}
