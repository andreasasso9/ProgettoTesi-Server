package com.example.tesi.service;


import com.example.tesi.entity.Prodotto;

import java.util.List;

public interface ProdottoService {
	public Prodotto getProdottoById(long id);
	public List<Prodotto> getAllProdotto();
	public Prodotto addProdotto(Prodotto prodotto);
	public boolean updateProdotto(Prodotto prodotto);
	public boolean deleteProdotto(long id);
}
