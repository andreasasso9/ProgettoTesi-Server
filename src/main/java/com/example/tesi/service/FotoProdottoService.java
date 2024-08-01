package com.example.tesi.service;

import com.example.tesi.entity.FotoByteArray;
import com.example.tesi.entity.Prodotto;

import java.util.List;

public interface FotoProdottoService {
	FotoByteArray getFotoById(long id);
	List<FotoByteArray> getAllFotos();
	boolean save(FotoByteArray foto);
	List<FotoByteArray> findByProdotto(Prodotto prodotto);
}
