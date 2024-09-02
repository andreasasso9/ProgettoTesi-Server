package com.tesi.repository;

import com.tesi.entity.Notifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NotificheRepository extends JpaRepository<Notifica, Long> {
	public List<Notifica> findByReceiver(String receiver);

	@Modifying
	@Transactional
	@Query("delete from Notifica where descrizione=?1")
	public int deleteByDescrizione(String descrizione);
}
