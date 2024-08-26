package com.example.tesi.control;

import com.example.tesi.entity.chat.Image;
import com.example.tesi.entity.chat.Text;
import com.example.tesi.service.TextService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class ChatController {

	private final SimpMessagingTemplate messagingTemplate;
	private final Gson gson;
	private TextService textService;

	@Autowired
	public ChatController(SimpMessagingTemplate messagingTemplate, Gson gson, TextService textService) {
		this.messagingTemplate = messagingTemplate;
		this.gson = gson;
		this.textService = textService;
	}

	@MessageMapping("/chat")
	public void send(String message) {
		Logger.getGlobal().info(message);

		Text text=gson.fromJson(message, Text.class);

		String destination="/queue/user/"+text.getReceiver();

		Image image;
		if (text.getText() == null) {
			image = gson.fromJson(message, Image.class);
			textService.save(image);
		} else
			textService.save(text);

		messagingTemplate.convertAndSend(destination, message);
	}

	@MessageMapping("/synchronize")
	public void synchronize(String user) {
		user=user.replace("\"", "");
		Logger.getGlobal().info(user+" is trying to synchronize");
		List<Text> texts = textService.findByReceiver(user);
		for (Text text : texts) {
			if (!text.isDelivered()) {
				String messageToSend = gson.toJson(text);
				messagingTemplate.convertAndSend("/queue/user/"+user, messageToSend);
				textService.deleteById(text.getId());
			}
		}
	}
}
