package com.tesi.service;

import com.tesi.entity.Token;

import java.util.Set;

public interface TokenService {
	Set<Token> findByUsername(String username);
	boolean save(Token token);
	boolean delete(Token token);
	Token findByToken(String token);
}
