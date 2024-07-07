package com.example.tesi.service;

import com.example.tesi.entity.Notifica;

import java.util.List;
import java.util.UUID;

public interface NotificheService {
	public boolean save(Notifica notifica);
	public List<Notifica> findByReceiver(UUID receiver);
	public int delete(String descrizione);
}
