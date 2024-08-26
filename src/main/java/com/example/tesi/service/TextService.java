package com.example.tesi.service;

import com.example.tesi.entity.chat.Text;

import java.util.List;

public interface TextService {
	void save(Text text);
	void deleteById(Long id);
	List<Text> findByReceiver(String receiver);
}
