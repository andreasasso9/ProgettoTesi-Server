package com.tesi.repository;

import com.tesi.entity.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, String> {
	@Query("select c from Chat c where c.user1=?1 or c.user2=?1")
	List<Chat> findByUser(String user);
}
