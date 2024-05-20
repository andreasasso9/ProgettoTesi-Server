package com.example.tesi.repository;

import com.example.tesi.entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
	//boolean update(Prodotto prodotto);
}
