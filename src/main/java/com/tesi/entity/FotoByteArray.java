package com.tesi.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class FotoByteArray implements Serializable {
	@Id @GeneratedValue
	private Long id;

	@Lob @Column(columnDefinition = "MEDIUMBLOB")
	private byte[] value;

	@ManyToOne
	private Prodotto prodotto;

	public FotoByteArray() {}

	public FotoByteArray(byte[] value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
}
