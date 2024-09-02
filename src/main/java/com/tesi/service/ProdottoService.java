package com.tesi.service;


import com.tesi.entity.Prodotto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdottoService {
	Prodotto findProdottoById(long id);
	List<Prodotto> getAllProdotto();
	Prodotto addProdotto(Prodotto prodotto);
	boolean updateProdotto(Prodotto prodotto);
	boolean deleteProdotto(long id);
	List<Prodotto> getAllProdottoNotOwnedBy(String user, Pageable pageable);
	List<Prodotto> findByProprietario(String proprietario);
	List<Prodotto> findByRicerca(String user, String text);
	List<Prodotto> findByCompratore(String compratore);
}
