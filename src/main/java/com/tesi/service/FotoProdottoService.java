package com.tesi.service;

import com.tesi.entity.FotoByteArray;
import com.tesi.entity.Prodotto;

import java.util.List;

public interface FotoProdottoService {
	FotoByteArray getFotoById(long id);
	List<FotoByteArray> getAllFotos();
	boolean save(FotoByteArray foto);
	List<FotoByteArray> findByProdotto(Prodotto prodotto);
	void deleteByProdotto(Prodotto prodotto);
}
