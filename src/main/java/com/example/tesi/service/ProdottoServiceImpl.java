package com.example.tesi.service;
import com.example.tesi.entity.Prodotto;
import com.example.tesi.entity.User;
import com.example.tesi.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
	public boolean updateProdotto(Prodotto prodotto) {
		return true;
	}

	@Override
	public boolean deleteProdotto(long id) {
		prodottoRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Prodotto> getAllProdottoNotOwnedBy(User user, Pageable pageable) {
		List<Prodotto> result=prodottoRepository.getAllProdottoNotOwnedBy(user.getId(), pageable);
		return result;
	}

	@Override
	public boolean miPiace(Long idProdotto) {
		Optional<Prodotto>oldProdotto=prodottoRepository.findById(idProdotto);
		if (oldProdotto.isPresent()) {
			Prodotto p=oldProdotto.get();
			p.setMiPiace(p.getMiPiace()+1);
			prodottoRepository.save(p);
			return true;
		}

		return false;
	}
}
