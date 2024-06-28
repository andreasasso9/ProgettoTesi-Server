package com.example.tesi.repository;

import com.example.tesi.entity.FotoByteArray;
import com.example.tesi.entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoProdottoRepository extends JpaRepository<FotoByteArray, Long> {
	public List<FotoByteArray> findByProdotto(Prodotto prodotto);
}
