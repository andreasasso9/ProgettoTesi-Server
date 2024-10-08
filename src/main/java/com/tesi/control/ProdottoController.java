package com.tesi.control;

import com.tesi.entity.Prodotto;
import com.tesi.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {
	private final ProdottoService prodottoService;
	private final Logger logger;

	@Autowired
	public ProdottoController(ProdottoService prodottoService) {
		this.prodottoService = prodottoService;
		logger = Logger.getLogger(this.getClass().getName());
	}

	@PostMapping("/addProdotto")
	public ResponseEntity<Prodotto> addProdotto(@RequestBody Prodotto prodotto) {
		logger.log(Level.INFO, "ADD PRODOTTO STARTED");
		Prodotto result = prodottoService.save(prodotto);
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
	public ResponseEntity<List<Prodotto>> getAllNotOwnedBy(@RequestParam String user, @RequestParam int page) {
		int PAGE_LIMIT=10;
		while (PAGE_LIMIT>0) {
			List<Prodotto> prodotti=getProdottiByPageable(page, PAGE_LIMIT, user);
			if (prodotti!=null && !prodotti.isEmpty())
				return ResponseEntity.ok(prodotti);
			PAGE_LIMIT /= 2;
		}

		logger.log(Level.INFO, "GET ALL PRODOTTO NOT OWNED BY FAILED");
		return ResponseEntity.notFound().build();
	}

	private List<Prodotto> getProdottiByPageable(int page, int limit, String user) {
		Pageable pageable= PageRequest.of(page, limit);
		List<Prodotto> prodotti=prodottoService.getAllProdottoNotOwnedBy(user.replace("\"", ""), pageable);
		if (prodotti!=null && !prodotti.isEmpty())
			return prodotti;
		return null;
	}

	@PostMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Long idProdotto) {
		logger.log(Level.INFO, "UPDATE PRODOTTO STARTED");
		Prodotto p=prodottoService.findProdottoByIdForUpdate(idProdotto);
		if (prodottoService.updateProdotto(p)) {
			logger.log(Level.INFO, "UPDATE SUCCESSFUL");
			return ResponseEntity.ok(true);
		}
		logger.log(Level.INFO, "UPDATE FAILED");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@PostMapping("/findByIdProprietario")
	public ResponseEntity<List<Prodotto>> findByIdProprietario(@RequestBody String user) {
		List<Prodotto> prodotti=prodottoService.findByProprietario(user.replace("\"", ""));
		if (prodotti!=null && !prodotti.isEmpty()) {
			logger.log(Level.INFO, "FIND BY PROPRIETARIO SUCCESSFUL");
			return ResponseEntity.ok(prodotti);
		}
		logger.log(Level.INFO, "FIND BY PROPRIETARIO FAILED");
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/findByRicerca")
	public ResponseEntity<List<Prodotto>> findByRicerca(@RequestParam String user, @RequestParam String text) {
		List<Prodotto> prodotti=prodottoService.findByRicerca(user.replace("\"", ""), text.replace("\"", ""));
		if (prodotti!=null) {
			logger.log(Level.INFO, "FIND PRODOTTO BY TITOLO O DESCRIZIONE SUCCESSFUL");
			return ResponseEntity.ok(prodotti);
		} else {
			logger.log(Level.INFO, "FIND PRODOTTO BY TITOLO O DESCRIZIONE FALIED");
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("/findByCompratore")
	public ResponseEntity<List<Prodotto>> findByCompratore(@RequestBody String compratore) {
		List<Prodotto> prodotti=prodottoService.findByCompratore(compratore.replace("\"", ""));
		if (prodotti!=null) {
			logger.log(Level.INFO, "FIND PRODOTTO BY COMPRATORE SUCCESSFUL");
			return ResponseEntity.ok(prodotti);
		} else {
			logger.log(Level.INFO, "FIND PRODOTTO BY COMPRATORE FAILED");
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> delete(@RequestBody Long id) {
		prodottoService.deleteProdotto(id);

		return ResponseEntity.ok(null);
	}

	@PostMapping("/findByLikedBy")
	public ResponseEntity<Set<Prodotto>> findByLikedBy(@RequestBody String username) {
		return ResponseEntity.ok(prodottoService.findByLikedBy(username.replace("\"", "")));
	}

	@PostMapping("/acquista")
	@Transactional
	public ResponseEntity<Boolean> acquista(@RequestParam String username, @RequestParam Long idProdotto) {
		Prodotto p=prodottoService.findProdottoByIdForUpdate(idProdotto);
		try {
			p.setCompratore(username.replace("\"", ""));
			if (prodottoService.save(p)!=null)
				return ResponseEntity.ok(true);
			return ResponseEntity.badRequest().build();
		} catch (RuntimeException e) {
			logger.info(e.getMessage());
			return ResponseEntity.badRequest().build();
		}

	}
}
