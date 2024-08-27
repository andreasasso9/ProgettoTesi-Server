package com.example.tesi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Token {
	@Id
	private String token;
	private String username;

	public Token() {}

	public Token(String token, String username) {
		this.token = token;
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
