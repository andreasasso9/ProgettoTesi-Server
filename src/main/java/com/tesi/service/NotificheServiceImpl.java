package com.tesi.service;

import com.tesi.entity.Notifica;
import com.tesi.repository.NotificheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificheServiceImpl implements NotificheService{
	private final NotificheRepository notificheRepository;

	@Autowired
	public NotificheServiceImpl(NotificheRepository notificheRepository) {
		this.notificheRepository = notificheRepository;
	}

	@Override
	public boolean save(Notifica notifica) {
		notificheRepository.save(notifica);
		return true;
	}

	@Override
	public List<Notifica> findByReceiver(String receiver) {
		return notificheRepository.findByReceiver(receiver);
	}

	@Override
	@Transactional
	public int delete(String descrizione) {
		return notificheRepository.deleteByDescrizione(descrizione);
	}
}
