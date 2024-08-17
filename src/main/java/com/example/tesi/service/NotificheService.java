package com.example.tesi.service;

import com.example.tesi.entity.Notifica;

import java.util.List;
import java.util.UUID;

public interface NotificheService {
	boolean save(Notifica notifica);
	List<Notifica> findByReceiver(String receiver);
	int delete(String descrizione);
}
