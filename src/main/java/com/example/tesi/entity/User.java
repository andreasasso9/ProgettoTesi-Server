package com.example.tesi.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
public class User implements Serializable {
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String username;
	private String password;
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String indirizzo;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Prodotto> prodottiPreferiti;

	public User(){}

	public User(String email, String username, String password, String indirizzo) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.indirizzo = indirizzo;
		prodottiPreferiti = new HashSet<>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Set<Prodotto> getProdottiPreferiti() {
		return prodottiPreferiti;
	}

	public void setProdottiPreferiti(Set<Prodotto> prodotti) {
		this.prodottiPreferiti = prodotti;
	}

	public void update(User user) {
		this.email = user.email;
		this.username = user.username;
		this.password = user.password;
		this.indirizzo = user.indirizzo;
		this.prodottiPreferiti = user.prodottiPreferiti;
	}

	@Override
	public String toString() {
		return String.format(Locale.ITALIAN, "%s %s", username, id);
	}
}
