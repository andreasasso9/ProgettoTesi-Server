package com.tesi.service;

import com.tesi.entity.chat.Chat;
import com.tesi.repository.ChatRepository;
import com.tesi.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
	private final ChatRepository chatRepository;
	private final TextRepository textRepository;

	@Autowired
	public ChatServiceImpl(ChatRepository chatRepository, TextRepository textRepository) {
		this.chatRepository = chatRepository;
		this.textRepository = textRepository;
	}

	@Override
	public Chat save(Chat chat) {
		return chatRepository.save(chat);
	}

	@Override
	public Chat findById(String id) {
		return chatRepository.findById(id).orElse(null);
	}

	@Override
	public boolean delete(String id, String username) {
		Chat chat=chatRepository.findById(id).orElse(null);

		if (chat!=null) {
			if (chat.getUser1().equalsIgnoreCase(username))
				chat.setUser1("");
			else if (chat.getUser2().equalsIgnoreCase(username))
				chat.setUser2("");
			chatRepository.save(chat);
			if (chat.getUser1()==null && chat.getUser2()==null) {
				textRepository.deleteByChatId(chat.getId());
				chatRepository.deleteById(chat.getId());
			}
		}
		return true;
	}

	@Override
	public List<Chat> findByUser(String username) {
		List<Chat> chats=chatRepository.findByUser(username);
		return chats;
	}
}
