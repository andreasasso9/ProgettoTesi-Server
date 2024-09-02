package com.tesi.service;

import com.tesi.entity.chat.Text;
import com.tesi.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextServiceImpl implements TextService{
	private TextRepository textRepository;

	@Autowired
	public TextServiceImpl(TextRepository textRepository) {
		this.textRepository = textRepository;
	}

	@Override
	public void save(Text text) {
		textRepository.save(text);
	}

	@Override
	public void deleteById(Long id) {
		textRepository.deleteById(id);
	}

	@Override
	public List<Text> findByReceiver(String receiver) {
		return textRepository.findByReceiver(receiver);
	}
}
