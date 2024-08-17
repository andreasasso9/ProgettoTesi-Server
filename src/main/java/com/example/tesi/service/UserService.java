package com.example.tesi.service;

import com.example.tesi.entity.User;

import java.util.UUID;

public interface UserService {
	User findUserById(UUID id);
	void saveUser(User user);
	void deleteUser(UUID id);
	User getUserByUsername(String username);
	boolean miPiace(String idUser, Long idProdotto);
	boolean update(User updatedUser);
}
