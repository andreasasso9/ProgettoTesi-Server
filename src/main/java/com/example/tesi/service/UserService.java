package com.example.tesi.service;

import com.example.tesi.entity.User;

import java.util.UUID;

public interface UserService {
	public User getUserById(UUID id);
	public void saveUser(User user);
	public void deleteUser(UUID id);
	public User getUserByUsername(String username);
	public boolean miPiace(UUID idUser, Long idProdotto);
}
