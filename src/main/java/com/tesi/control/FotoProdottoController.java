package com.tesi.control;

import com.tesi.entity.FotoByteArray;
import com.tesi.entity.Prodotto;
import com.tesi.service.FotoProdottoService;
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
		fotoProdottoService.deleteByIdProdotto(foto.getFirst().getIdProdotto());
		foto.forEach(fotoProdottoService::save);
		Logger.getGlobal().log(Level.INFO, "FOTO ADDED");
		return ResponseEntity.ok(true);
	}

	@PostMapping("/findByIdProdotto")
	public ResponseEntity<List<FotoByteArray>> findByProdotto(@RequestBody Long idProdotto) {
		List<FotoByteArray> result=fotoProdottoService.findByIdProdotto(idProdotto);
		if (result!=null)
			return ResponseEntity.ok(result);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping("/findFirst")
	public ResponseEntity<FotoByteArray> findFirst(@RequestBody Long idProdotto) {
		List<FotoByteArray> foto=findByProdotto(idProdotto).getBody();
		if (foto!=null && !foto.isEmpty())
			return ResponseEntity.ok(foto.getFirst());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping("/deleteByIdProdotto")
	ResponseEntity<Void> deleteByIdProdotto(@RequestBody Long id) {
		fotoProdottoService.deleteByIdProdotto(id);

		Logger.getGlobal().info("DELETE FOTO SUCCESSFUL");
		return ResponseEntity.ok(null);
	}
}
