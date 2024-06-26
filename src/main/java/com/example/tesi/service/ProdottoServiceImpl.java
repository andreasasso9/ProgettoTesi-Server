package com.example.tesi.service;

import com.example.tesi.entity.Prodotto;
import com.example.tesi.entity.User;
import com.example.tesi.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	public Prodotto addProdotto(Prodotto prodotto) {
		return prodottoRepository.save(prodotto);
	}

	@Override
	public boolean updateProdotto(Prodotto newProdotto) {
		//Logger.getGlobal().log(Level.INFO, prodotto.getMiPiace()+"");
		Prodotto oldProdotto=prodottoRepository.findById(newProdotto.getId()).orElse(null);
		if(oldProdotto!=null) {
			oldProdotto.update(newProdotto);
			prodottoRepository.save(oldProdotto);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteProdotto(long id) {
		prodottoRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Prodotto> getAllProdottoNotOwnedBy(User user, Pageable pageable) {
		return prodottoRepository.getAllProdottoNotOwnedBy(user.getId(), pageable);
	}
}
