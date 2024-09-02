package com.tesi.entity.chat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

@Entity
public class Image extends Text{
	@Lob @Column(columnDefinition = "MEDIUMBLOB")
	private String foto;

	public Image(String foto, String sender, String receiver) {
		super(null, sender, receiver);
		this.foto=foto;
		delivered=false;
	}

	public Image() {}

	public String getFoto() {
		return foto;
	}
}
