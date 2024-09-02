package com.tesi.service;

import com.tesi.entity.Prodotto;
import com.tesi.entity.User;
import com.tesi.repository.UserRepository;
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
	public boolean miPiace(String username, Long idProdotto) {
		User user=userRepository.findByUsername(username);
		Prodotto prodotto=new Prodotto();
		prodotto.setId(idProdotto);

		assert user != null;
		user.getProdottiPreferiti().add(prodotto);

		userRepository.save(user);
		return true;
	}

	@Override
	public boolean update(User updatedUser) {
		User olduser=userRepository.findById(updatedUser.getId()).orElse(null);
		if (olduser != null) {
			olduser.update(updatedUser);
			userRepository.save(olduser);
			return true;
		}

		return false;
	}
}
