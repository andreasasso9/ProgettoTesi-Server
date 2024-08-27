package com.example.tesi.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

@Service
public class PushNotificationService {
	public PushNotificationService() throws IOException {
		FileInputStream serviceAccount =
				new FileInputStream("src/main/resources/clonevinted-firebase-adminsdk-36uxv-3979b2df47.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		FirebaseApp.initializeApp(options);
	}

	public void sendPushNotification(String token, String title, String body) {
		Notification notification=Notification.builder().setTitle(title)
				.setBody(body)
				.build();

		Message message = Message.builder()
				.setToken(token)
				.setNotification(notification)
				.build();

		FirebaseMessaging.getInstance().sendAsync(message).addListener(()-> Logger.getGlobal().info(notification+" sent"), Runnable::run);
	}
}
