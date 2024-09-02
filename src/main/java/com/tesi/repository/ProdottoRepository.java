package com.tesi.repository;

import com.tesi.entity.Prodotto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
	@Query(value = "select distinct p from Prodotto p where p.proprietario != ?1 and p.compratore is null order by p.miPiace desc")
	List<Prodotto> getAllProdottoNotOwnedBy(String proprietario, Pageable pageable);

	List<Prodotto> findByProprietario(String proprietario);

	@Query("select distinct p from Prodotto p where p.proprietario!=?1 and (p.titolo like %?2% or p.descrizione like %?2%) and p.compratore is null order by p.miPiace")
	List<Prodotto> findByRicerca(String currentUser, String text);

	List<Prodotto> findByCompratore(String compratore);
}
