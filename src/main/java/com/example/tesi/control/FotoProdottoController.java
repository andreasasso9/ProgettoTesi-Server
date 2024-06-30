package com.example.tesi.control;

import com.example.tesi.entity.FotoByteArray;
import com.example.tesi.entity.Prodotto;
import com.example.tesi.service.FotoProdottoService;
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
@RequestMapping("/prodotto/foto")
public class FotoProdottoController {
	private final FotoProdottoService fotoProdottoService;

	@Autowired
	public FotoProdottoController(FotoProdottoService fotoProdottoService) {
		this.fotoProdottoService = fotoProdottoService;
	}

	@PostMapping("/addFoto")
	public ResponseEntity<Boolean> addFotoProdotto(@RequestBody List<FotoByteArray> foto) {
		Logger.getGlobal().log(Level.INFO, "ADD FOTO STARTED");
		foto.forEach(fotoProdottoService::save);
		Logger.getGlobal().log(Level.INFO, "FOTO ADDED");
		return ResponseEntity.ok(true);
	}

	@PostMapping("/findByProdotto")
	public ResponseEntity<List<FotoByteArray>> findByProdotto(@RequestBody Prodotto prodotto) {
		List<FotoByteArray> result=fotoProdottoService.findByProdotto(prodotto);
		if (result!=null)
			return ResponseEntity.ok(result);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping("/findFirst")
	public ResponseEntity<FotoByteArray> findFirst(@RequestBody Prodotto prodotto) {
		List<FotoByteArray> foto=findByProdotto(prodotto).getBody();
		if (foto!=null && !foto.isEmpty())
			return ResponseEntity.ok(foto.getFirst());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
