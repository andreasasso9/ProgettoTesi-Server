package com.tesi.service;

import com.tesi.entity.Prodotto;
import com.tesi.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProdottoServiceImpl implements ProdottoService{
	private final ProdottoRepository prodottoRepository;

	@Autowired
	public ProdottoServiceImpl(ProdottoRepository prodottoRepository) {
		this.prodottoRepository = prodottoRepository;
	}

	@Override
	public Prodotto findProdottoById(long id) {
		return prodottoRepository.findById(id).orElse(null);
	}

	@Override
	public List<Prodotto> getAllProdotto() {
		return prodottoRepository.findAll();
	}

	public Prodotto save(Prodotto prodotto) {
		return prodottoRepository.save(prodotto);
	}

	@Override
	public boolean updateProdotto(Prodotto updatedProdotto) {
		Prodotto oldProdotto=prodottoRepository.findByIdForUpdate(updatedProdotto.getId());
		if(oldProdotto!=null) {
			oldProdotto.update(updatedProdotto);
			prodottoRepository.save(oldProdotto);
			return true;
		}
		return false;
	}

	@Override
	public void deleteProdotto(long id) {
		prodottoRepository.deleteById(id);
	}

	@Override
	public List<Prodotto> getAllProdottoNotOwnedBy(String user, Pageable pageable) {
		return prodottoRepository.getAllProdottoNotOwnedBy(user, pageable);
	}

	@Override
	public List<Prodotto> findByProprietario(String proprietario) {
		return prodottoRepository.findByProprietario(proprietario);
	}

	@Override
	public List<Prodotto> findByRicerca(String user, String text) {
		return prodottoRepository.findByRicerca(user, text);
	}

	@Override
	public List<Prodotto> findByCompratore(String compratore) {
		return prodottoRepository.findByCompratore(compratore);
	}

	@Override
	public Set<Prodotto> findByLikedBy(String username) {
		return prodottoRepository.findByLikedBy(username);
	}

	@Override
	public Prodotto findProdottoByIdForUpdate(long id) {
		return prodottoRepository.findByIdForUpdate(id);
	}
}
