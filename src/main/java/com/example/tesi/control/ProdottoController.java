package com.example.tesi.control;

import com.example.tesi.entity.FotoByteArray;
import com.example.tesi.entity.Prodotto;
import com.example.tesi.entity.User;
import com.example.tesi.entity.entityoptions.Brand;
import com.example.tesi.entity.entityoptions.Categoria;
import com.example.tesi.entity.entityoptions.Condizioni;
import com.example.tesi.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
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

	@PostMapping("/add")
	public ResponseEntity<Boolean> addProdotto(@RequestBody Prodotto prodotto) {
		logger.log(Level.INFO, "ADD PRODOTTO STARTED");
		if (prodottoService.addProdotto(prodotto)) {
			logger.log(Level.INFO, "Add successful");
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} else {
			logger.log(Level.INFO, "Add failed");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
}
