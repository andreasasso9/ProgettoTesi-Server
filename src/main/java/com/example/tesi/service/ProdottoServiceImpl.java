package com.example.tesi.service;

import com.example.tesi.entity.FotoByteArray;
import com.example.tesi.entity.Prodotto;
import com.example.tesi.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoServiceImpl implements ProdottoService{
	private final ProdottoRepository prodottoRepository;
	private final FotoProdottoService fotoProdottoService;

	@Autowired
	public ProdottoServiceImpl(ProdottoRepository prodottoRepository, FotoProdottoService fotoProdottoService) {
		this.prodottoRepository = prodottoRepository;
		this.fotoProdottoService = fotoProdottoService;
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
	public Prodotto addProdotto(Prodotto prodotto) {
		return prodottoRepository.save(prodotto);
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
