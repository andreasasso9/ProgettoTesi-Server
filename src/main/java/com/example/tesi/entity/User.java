package com.example.tesi.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Entity
public class User implements Serializable {
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String username;
	private String password;
	@Id
	private UUID id;
	private String indirizzo;
	@OneToMany
	@JoinColumn(name = "prodotti")
	private List<Prodotto> prodotti;

	public User(){}

	public User(String email, String username, String password, String indirizzo) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.id = UUID.randomUUID();
		this.indirizzo =indirizzo;
		prodotti=new ArrayList<>();
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

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	@Override
	public String toString() {
		return String.format(Locale.ITALIAN, "%s %s", username, id);
	}
}
