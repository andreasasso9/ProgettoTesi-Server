package com.example.tesi.service;


import com.example.tesi.entity.Prodotto;
import com.example.tesi.entity.User;
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
