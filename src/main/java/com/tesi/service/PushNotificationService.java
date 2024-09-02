package com.tesi.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class PushNotificationService {

	public PushNotificationService() {
		try (FileInputStream serviceAccount=new FileInputStream("src/main/resources/clone-vinted-dd1fe-firebase-adminsdk-7vovk-b97ca48865.json")) {
			FirebaseOptions options=new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();

			FirebaseApp.initializeApp(options);
		} catch (IOException ignored) {}
	}

	public void send(String token, String title, String body) {
		Notification notification=Notification.builder()
				.setTitle(title)
				.setBody(body)
				.build();

		Message message= Message.builder()
				.setNotification(notification)
				.setToken(token)
				.build();

		FirebaseMessaging.getInstance().sendAsync(message);
	}
}
