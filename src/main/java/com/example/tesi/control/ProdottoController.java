package com.example.tesi.control;

import com.example.tesi.entity.Prodotto;
import com.example.tesi.entity.User;
import com.example.tesi.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {
	private final ProdottoService prodottoService;
	private final Logger logger;
	private final int PAGE_LIMIT=20;

	@Autowired
	public ProdottoController(ProdottoService prodottoService) {
		this.prodottoService = prodottoService;
		logger = Logger.getLogger(this.getClass().getName());
	}

	@PostMapping("/addProdotto")
	public ResponseEntity<Prodotto> addProdotto(@RequestBody Prodotto prodotto) {
		logger.log(Level.INFO, "ADD PRODOTTO STARTED");
		Prodotto result = prodottoService.addProdotto(prodotto);
		if (result!=null) {
			logger.log(Level.INFO, "ADD SUCCESSFUL");
			return ResponseEntity.ok(result);
		}

		logger.log(Level.INFO, "ADD FAILED");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@PostMapping("/getAll")
	public ResponseEntity<List<Prodotto>> getAll(@RequestBody Integer limit) {
		logger.log(Level.INFO, "GET ALL PRODOTTO STARTED");
		List<Prodotto> prodotti=prodottoService.getAllProdotto();
		if (prodotti.size() > limit)
			prodotti = prodotti.subList(0, limit);

		if (!prodotti.isEmpty()) {
			logger.log(Level.INFO, "GET ALL PRODOTTO SUCCESSFUL");
			return ResponseEntity.ok(prodotti);
		}

		logger.log(Level.INFO, "GET ALL PRODOTTO FAILED");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@PostMapping("/getAllNotOwnedBy")
	public ResponseEntity<List<Prodotto>> getAllNotOwnedBy(@RequestBody User user) {
		Pageable pageable= PageRequest.of(0, PAGE_LIMIT);
		List<Prodotto> prodotti=prodottoService.getAllProdottoNotOwnedBy(user, pageable);
		if (prodotti!=null && !prodotti.isEmpty()) {
			logger.log(Level.INFO, "GET ALL PRODOTTO NOT OWNED BY SUCCESSFUL");
			return ResponseEntity.ok(prodotti);
		}

		logger.log(Level.INFO, "GET ALL PRODOTTO NOT OWNED BY FAILED");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@PostMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Prodotto prodotto) {
		logger.log(Level.INFO, "UPDATE PRODOTTO STARTED");
		if (prodottoService.updateProdotto(prodotto)) {
			logger.log(Level.INFO, "UPDATE SUCCESSFUL");
			return ResponseEntity.ok(true);
		}
		logger.log(Level.INFO, "UPDATE FAILED");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@PostMapping("/findByIdProprietario")
	public ResponseEntity<List<Prodotto>> findByIdProprietario(@RequestBody UUID user) {
		List<Prodotto> prodotti=prodottoService.findByIdProprietario(user);
		if (prodotti!=null && !prodotti.isEmpty()) {
			logger.log(Level.INFO, "GET ALL PRODOTTO SUCCESSFUL");
			return ResponseEntity.ok(prodotti);
		}
		logger.log(Level.INFO, "GET ALL PRODOTTO FAILED");
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/findByTitoloODescrizione")
	public ResponseEntity<List<Prodotto>> findByTitoloODescrizione(@RequestParam UUID user, @RequestParam String text) {
		List<Prodotto> prodotti=prodottoService.findByTitoloODescrizione(user, text.replace("\"", ""));
		if (prodotti!=null) {
			logger.log(Level.INFO, "GET PRODOTTO BY TITOLO O DESCRIZIONE SUCCESSFUL");
			return ResponseEntity.ok(prodotti);
		} else {
			logger.log(Level.INFO, "GET PRODOTTO BY TITOLO O DESCRIZIONE FALIED");
			return ResponseEntity.internalServerError().build();
		}
	}
}
