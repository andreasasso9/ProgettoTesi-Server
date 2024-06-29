package com.example.tesi.repository;

import com.example.tesi.entity.Prodotto;
import com.example.tesi.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
	@Query(value = "select p from Prodotto p where p.idProprietario != ?1")
	public List<Prodotto> getAllProdottoNotOwnedBy(UUID idUser, Pageable pageable);
}
