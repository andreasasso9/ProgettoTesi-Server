package com.example.tesi.control;

import com.example.tesi.entity.Prodotto;
import com.example.tesi.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {
	private final ProdottoService prodottoService;
	private Logger logger;

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
		} else {
			logger.log(Level.INFO, "ADD FAILED");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
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
		} else {
			logger.log(Level.INFO, "GET ALL PRODOTTO FAILED");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
