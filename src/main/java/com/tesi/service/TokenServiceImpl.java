package com.tesi.service;

import com.tesi.entity.Token;
import com.tesi.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TokenServiceImpl implements TokenService {
	private final TokenRepository tokenRepository;

	@Autowired
	public TokenServiceImpl(TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}

	@Override
	public boolean save(Token token) {
		tokenRepository.save(token);
		return true;
	}

	@Override
	public boolean delete(Token token) {
		tokenRepository.delete(token);
		return true;
	}

	@Override
	public Set<Token> findByUsername(String username) {
		return tokenRepository.findByUsername(username);
	}

	@Override
	public Token findByToken(String token) {
		return tokenRepository.findById(token).orElse(null);
	}
}
