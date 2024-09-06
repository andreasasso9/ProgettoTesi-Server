package com.tesi.repository;

import com.tesi.entity.Prodotto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
	@Query("select  p from Prodotto p where p.proprietario != ?1 and p.compratore is null")
	List<Prodotto> getAllProdottoNotOwnedBy(String proprietario, Pageable pageable);

	List<Prodotto> findByProprietario(String proprietario);

	@Query("select distinct p from Prodotto p where p.proprietario!=?1 and (p.titolo like %?2% or p.descrizione like %?2%) and p.compratore is null")
	List<Prodotto> findByRicerca(String currentUser, String text);

	List<Prodotto> findByCompratore(String compratore);

	@Query("select p from Prodotto p join p.likedBy u where u.username = ?1")
	Set<Prodotto> findByLikedBy(String username);
}
