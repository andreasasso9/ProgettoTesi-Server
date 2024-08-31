package com.example.tesi.service;

import com.example.tesi.entity.FotoByteArray;
import com.example.tesi.entity.Prodotto;
import com.example.tesi.repository.FotoProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FotoProdottoServiceImpl implements FotoProdottoService{
	private final FotoProdottoRepository fotoProdottoRepository;

	@Autowired
	public FotoProdottoServiceImpl(FotoProdottoRepository repository) {
		this.fotoProdottoRepository = repository;
	}


	@Override
	public FotoByteArray getFotoById(long id) {
		return fotoProdottoRepository.getReferenceById(id);
	}

	@Override
	public List<FotoByteArray> getAllFotos() {
		return fotoProdottoRepository.findAll();
	}

	@Override
	public boolean save(FotoByteArray foto) {
		Logger.getGlobal().log(Level.INFO, "SAVING FOTO");

		fotoProdottoRepository.save(foto);
		Logger.getGlobal().log(Level.INFO, "FOTO SAVED");
		return true;
	}

	@Override
	public List<FotoByteArray> findByProdotto(Prodotto prodotto) {
		return fotoProdottoRepository.findByProdotto(prodotto);
	}

	@Override
	public void deleteByProdotto(Prodotto prodotto) {
		fotoProdottoRepository.deleteByProdotto(prodotto);
	}


}
