package com.example.tesi.control;

import com.example.tesi.entity.FotoByteArray;
import com.example.tesi.entity.Notifica;
import com.example.tesi.entity.Prodotto;
import com.example.tesi.entity.User;
import com.example.tesi.service.FotoProdottoService;
import com.example.tesi.service.NotificheService;
import com.example.tesi.service.ProdottoService;
import com.example.tesi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	private final NotificheService notificheService;
	private final ProdottoService prodottoService;
	private final UserService userService;
	private final FotoProdottoService fotoProdottoService;

	@Autowired
	public NotificheController(NotificheService notificheService, ProdottoService prodottoService, UserService userService, FotoProdottoService fotoProdottoService) {
		this.notificheService = notificheService;
		this.prodottoService = prodottoService;
		this.userService = userService;
		this.fotoProdottoService = fotoProdottoService;
	}

	public void miPiace(UUID idSender, Long idProdotto) {
		User sender=userService.findUserById(idSender);
		Prodotto prodotto=prodottoService.findProdottoById(idProdotto);
		FotoByteArray foto=fotoProdottoService.findByProdotto(prodotto).getFirst();

		String descrizione=String.format("%s ha messo mi piace al tuo articolo %s", sender.getUsername(), prodotto.getTitolo());

		notificheService.save(new Notifica(idSender, prodotto.getIdProprietario(), descrizione, foto.getValue()));
	}

	@PostMapping("/findByIdReceiver")
	public ResponseEntity<List<Notifica>> findByIdreceiver(@RequestBody UUID receiver) {
		List<Notifica> notifiche=notificheService.findByReceiver(receiver);

		if (!notifiche.isEmpty())
			return ResponseEntity.ok(notifiche);
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete(@RequestBody String descrizione) {
		if (notificheService.delete(descrizione.replace("\"", ""))==0)
			return ResponseEntity.ok(true);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
