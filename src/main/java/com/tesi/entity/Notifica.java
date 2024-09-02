package com.tesi.entity;

import jakarta.persistence.*;

@Entity
public class Notifica {
	@Id @GeneratedValue
	private Long id;
	private String sender, receiver;
	private String descrizione;
	@Lob @Column(columnDefinition = "MEDIUMBLOB")
	private byte[] foto;

	public Notifica() {}

	public Notifica(String sender, String receiver, String descrizione, byte[] foto) {
		this.sender = sender;
		this.receiver = receiver;
		this.descrizione = descrizione;
		this.foto = foto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
}
