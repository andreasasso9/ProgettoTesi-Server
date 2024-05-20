package com.example.tesi.service;

import com.example.tesi.entity.Prodotto;
import com.example.tesi.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoServiceImpl implements ProdottoService{
	private final ProdottoRepository prodottoRepository;

	@Autowired
	public ProdottoServiceImpl(ProdottoRepository prodottoRepository) {
		this.prodottoRepository = prodottoRepository;
	}

	@Override
	public Prodotto getProdottoById(long id) {
		return prodottoRepository.findById(id).orElse(null);
	}

	@Override
	public List<Prodotto> getAllProdotto() {
		return prodottoRepository.findAll();
	}

	@Override
	public boolean addProdotto(Prodotto prodotto) {
		prodottoRepository.save(prodotto);
		return true;
	}

	@Override
	public boolean updateProdotto(Prodotto prodotto) {
		//TODO completare metodo update
		//prodottoRepository.update(prodotto);
		return true;
	}

	@Override
	public boolean deleteProdotto(long id) {
		prodottoRepository.deleteById(id);
		return true;
	}
}
