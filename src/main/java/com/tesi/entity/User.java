package com.tesi.entity;

import jakarta.persistence.*;

import javax.annotation.ManagedBean;
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
//	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
//	private Set<Prodotto> prodottiPreferiti;

	public User(){}

	public User(String email, String username, String password, String indirizzo) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.indirizzo = indirizzo;
		//prodottiPreferiti = new HashSet<>();
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

//	public Set<Prodotto> getProdottiPreferiti() {
//		return prodottiPreferiti;
//	}

//	public void setProdottiPreferiti(Set<Prodotto> prodotti) {
//		this.prodottiPreferiti = prodotti;
//	}

	public void update(User user) {
		this.email = user.email;
		this.username = user.username;
		this.password = user.password;
		this.indirizzo = user.indirizzo;
//		this.prodottiPreferiti.clear();
//		this.prodottiPreferiti.addAll(user.getProdottiPreferiti());
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
}
