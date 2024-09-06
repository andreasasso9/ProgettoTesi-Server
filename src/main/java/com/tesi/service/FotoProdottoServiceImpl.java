package com.tesi.service;

import com.tesi.entity.FotoByteArray;
import com.tesi.repository.FotoProdottoRepository;
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
	public List<FotoByteArray> findByIdProdotto(Long idProdotto) {
		return fotoProdottoRepository.findByIdProdotto(idProdotto);
	}

	@Override
	public void deleteByIdProdotto(Long idProdotto) {
		fotoProdottoRepository.deleteByIdProdotto(idProdotto);
	}


}
