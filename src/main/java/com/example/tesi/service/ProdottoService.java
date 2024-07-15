package com.example.tesi.service;


import com.example.tesi.entity.Prodotto;
import com.example.tesi.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface ProdottoService {
	Prodotto findProdottoById(long id);
	List<Prodotto> getAllProdotto();
	Prodotto addProdotto(Prodotto prodotto);
	boolean updateProdotto(Prodotto prodotto);
	boolean deleteProdotto(long id);
	List<Prodotto> getAllProdottoNotOwnedBy(User user, Pageable pageable);
	List<Prodotto> findByIdProprietario(UUID idProprietario);
	List<Prodotto> findByTitoloODescrizione(UUID user, String text);
}
