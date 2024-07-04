package com.example.tesi.control;

import com.example.tesi.entity.Notifica;
import com.example.tesi.service.NotificheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifiche")
public class NotificheController {
	private NotificheService notificheService;

	@Autowired
	public NotificheController(NotificheService notificheService) {
		this.notificheService = notificheService;
	}

	@PostMapping("/save")
	public ResponseEntity<Boolean> save(@RequestBody Notifica notifica) {
		notificheService.save(notifica);
		return ResponseEntity.ok(true);
	}

	@PostMapping("/findByReceiver")
	public ResponseEntity<List<Notifica>> findByReceiver(@RequestBody UUID receiver) {
		List<Notifica> notifiche = notificheService.findByReceiver(receiver);
		if (!notifiche.isEmpty())
			return ResponseEntity.ok(notifiche);
		return ResponseEntity.notFound().build();

	}
}
