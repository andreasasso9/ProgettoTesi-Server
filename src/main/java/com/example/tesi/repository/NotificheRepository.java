package com.example.tesi.repository;

import com.example.tesi.entity.Notifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificheRepository extends JpaRepository<Notifica, Long> {
	public List<Notifica> findByReceiver(UUID receiver);
}
