package com.example.tesi.control;

import com.example.tesi.chat.Text;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

@Controller
public class ChatController {

	private final SimpMessagingTemplate messagingTemplate;

	@Autowired
	public ChatController(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@MessageMapping("/chat")
	public void send(String message) {
		Logger.getGlobal().info(message);

		Gson gson=new Gson();
		Text text= gson.fromJson(message, Text.class);

		String destination="/queue/user/"+text.getReceiver();

		messagingTemplate.convertAndSend(destination, message);
	}
}
