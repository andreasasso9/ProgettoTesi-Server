package com.tesi.entity;

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
	@Lob @Column(columnDefinition = "MEDIUMBLOB")
	private byte[] foto;

	public User(){}

	public User(String email, String username, String password, String indirizzo) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.indirizzo = indirizzo;

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

	public void update(User user) {
		email = user.email;
		username = user.username;
		password = user.password;
		indirizzo = user.indirizzo;
		foto = user.foto;
	}

	@Override
	public String toString() {
		return String.format(Locale.ITALIAN, "%s %s", username, id);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, username, password, id, indirizzo);
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
}
