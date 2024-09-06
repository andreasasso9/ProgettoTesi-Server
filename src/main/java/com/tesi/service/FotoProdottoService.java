package com.tesi.service;

import com.tesi.entity.FotoByteArray;

import java.util.List;

public interface FotoProdottoService {
	FotoByteArray getFotoById(long id);
	List<FotoByteArray> getAllFotos();
	boolean save(FotoByteArray foto);
	List<FotoByteArray> findByIdProdotto(Long idProdotto);
	void deleteByIdProdotto(Long idProdotto);
}
