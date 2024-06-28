package com.example.tesi.service;

import com.example.tesi.entity.FotoByteArray;
import com.example.tesi.entity.Prodotto;

import java.util.List;

public interface FotoProdottoService {
	public FotoByteArray getFotoById(long id);
	public List<FotoByteArray> getAllFotos();
	public boolean save(FotoByteArray foto);
	public List<FotoByteArray> findByProdotto(Prodotto prodotto);
}
