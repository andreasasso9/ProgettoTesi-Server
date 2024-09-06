package com.tesi.control;

import com.tesi.entity.FotoByteArray;
import com.tesi.entity.Notifica;
import com.tesi.entity.Prodotto;
import com.tesi.entity.Token;
import com.tesi.service.*;

import com.tesi.service.FotoProdottoService;
import com.tesi.service.NotificheService;
import com.tesi.service.ProdottoService;
import com.tesi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/notifiche")
public class NotificheController {
	private final NotificheService notificheService;
	private final ProdottoService prodottoService;
	private final FotoProdottoService fotoProdottoService;
	private final PushNotificationService pushNotificationService;
	private final TokenService tokenService;

	@Autowired
	public NotificheController(NotificheService notificheService, ProdottoService prodottoService, FotoProdottoService fotoProdottoService, PushNotificationService pushNotificationService, TokenService tokenService) {
		this.notificheService = notificheService;
		this.prodottoService = prodottoService;
		this.fotoProdottoService = fotoProdottoService;
		this.pushNotificationService = pushNotificationService;
		this.tokenService = tokenService;
	}

	@PostMapping("/miPiace")
	public void miPiace(String sender, Long idProdotto) {
		Prodotto prodotto=prodottoService.findProdottoById(idProdotto);
		FotoByteArray foto=fotoProdottoService.findByIdProdotto(idProdotto).getFirst();

		String descrizione=String.format("%s ha messo mi piace al tuo articolo %s", sender, prodotto.getTitolo());

		notificheService.save(new Notifica(sender, prodotto.getProprietario(), descrizione, foto.getValue()));

		Set<Token> tokens=tokenService.findByUsername(prodotto.getProprietario());

		for (Token t:tokens)
			pushNotificationService.send(t.getToken(), sender, "Ha messo mi piace al tuo articolo "+prodotto.getTitolo());
	}

	@PostMapping("/findByReceiver")
	public ResponseEntity<List<Notifica>> findByReceiver(@RequestBody String receiver) {
		List<Notifica> notifiche=notificheService.findByReceiver(receiver.replace("\"", ""));

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

	@PostMapping("/save")
	public ResponseEntity<Boolean> save(@RequestBody Notifica notifica) {
		if (notificheService.save(notifica))
			return ResponseEntity.ok(true);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
