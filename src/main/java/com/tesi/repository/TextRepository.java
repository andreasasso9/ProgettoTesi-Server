package com.tesi.repository;

import com.tesi.entity.chat.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {
	List<Text> findByReceiver(String receiver);
	List<Text> findByChatId(String chatId);

	@Transactional
	void deleteByChatId(String chatId);
}
