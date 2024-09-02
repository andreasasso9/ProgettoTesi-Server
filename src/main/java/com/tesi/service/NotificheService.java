package com.tesi.service;

import com.tesi.entity.Notifica;

import java.util.List;

public interface NotificheService {
	boolean save(Notifica notifica);
	List<Notifica> findByReceiver(String receiver);
	int delete(String descrizione);
}
