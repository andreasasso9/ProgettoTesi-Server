package com.tesi.entity.chat;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Text {
	private String text;
	private String sender, receiver;
	protected boolean delivered;
	@Id
	@GeneratedValue
	private Long id;

	public Text(String text, String sender, String receiver) {
		this.text = text;
		this.sender = sender;
		this.receiver = receiver;
		delivered = false;
	}

	public Text() {}

	public Long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
}
