package com.tesi.control;

import com.tesi.entity.Token;
import com.tesi.entity.chat.Chat;
import com.tesi.entity.chat.Image;
import com.tesi.entity.chat.Text;
import com.tesi.service.ChatService;
import com.tesi.service.PushNotificationService;
import com.tesi.service.TextService;
import com.tesi.service.TokenService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Controller
@RequestMapping("/chat")
public class ChatController {

	private final SimpMessagingTemplate messagingTemplate;
	private final Gson gson;
	private final TextService textService;
	private final PushNotificationService pushNotificationService;
	private final TokenService tokenService;
	private final ChatService chatService;

	@Autowired
	public ChatController(SimpMessagingTemplate messagingTemplate, Gson gson, TextService textService, PushNotificationService pushNotificationService, TokenService tokenService, ChatService chatService) {
		this.messagingTemplate = messagingTemplate;
		this.gson = gson;
		this.textService = textService;
		this.pushNotificationService = pushNotificationService;
		this.tokenService = tokenService;
		this.chatService = chatService;
	}

	@MessageMapping("/send")
	public void send(String message) {
		Logger.getGlobal().info(message);

		Text text=gson.fromJson(message, Text.class);

		Chat chat=chatService.findById(text.getChatId());
		if (chat.getUser1().isEmpty())
			chat.setUser1(text.getReceiver());
		if (chat.getUser2().isEmpty())
			chat.setUser2(text.getReceiver());

		chatService.save(chat);

		String destination="/queue/user/"+text.getReceiver();

		Image image;
		if (text.getText() == null) {
			image = gson.fromJson(message, Image.class);
			textService.save(image);
		} else
			textService.save(text);

		messagingTemplate.convertAndSend(destination, message);

		Set<Token> tokens=tokenService.findByUsername(text.getReceiver());

		for (Token t:tokens)
			pushNotificationService.send(t.getToken(), text.getSender(), text.getText());
	}

//	@MessageMapping("/synchronize")
//	public void synchronize(String user) {
//		user=user.replace("\"", "");
//		Logger.getGlobal().info(user+" is trying to synchronize");
//		List<Text> texts = textService.findByReceiver(user);
//		for (Text text : texts) {
//			if (!text.isDelivered()) {
//				String messageToSend = gson.toJson(text);
//				messagingTemplate.convertAndSend("/queue/user/"+user, messageToSend);
//				textService.deleteById(text.getId());
//			}
//		}
//	}

	@PostMapping("/save")
	public ResponseEntity<Chat> save(@RequestBody Chat chat) {
		Chat savedChat=chatService.save(chat);
		if (savedChat!=null)
			return ResponseEntity.ok(savedChat);
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/findById")
	public ResponseEntity<Chat> findById(@RequestBody String id) {
		Chat chat=chatService.findById(id.replace("\"", ""));
		if (chat!=null) {
			chat.setTexts(textService.findByChatId(chat.getId().replace("\"", "")));
			return ResponseEntity.ok(chat);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete(@RequestParam String id, @RequestParam String username) {
		boolean response=chatService.delete(id.replace("\"", ""), username.replace("\"", ""));
		if (response)
			return ResponseEntity.ok(true);
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/findByUser")
	public ResponseEntity<List<Chat>> findByUser(@RequestBody String user) {
		List<Chat> chats=chatService.findByUser(user.replace("\"", ""));
		if (chats != null) {
			for (Chat c:chats)
				c.setTexts(textService.findByChatId(c.getId().replace("\"", "")));
			return ResponseEntity.ok(chats);
		}
		return ResponseEntity.notFound().build();
	}
}
