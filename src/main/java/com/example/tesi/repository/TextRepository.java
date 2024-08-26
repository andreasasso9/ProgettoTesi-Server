package com.example.tesi.repository;

import com.example.tesi.entity.chat.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {
	List<Text> findByReceiver(String receiver);
}
