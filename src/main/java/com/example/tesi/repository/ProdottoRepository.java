package com.example.tesi.repository;

import com.example.tesi.entity.Prodotto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
	@Query(value = "select distinct p from Prodotto p where p.idProprietario != ?1 and p.idCompratore is null order by p.miPiace desc")
	List<Prodotto> getAllProdottoNotOwnedBy(UUID idUser, Pageable pageable);

	List<Prodotto> findByIdProprietario(UUID idProprietario);

	@Query("select distinct p from Prodotto p where p.idProprietario!=?1 and (p.titolo like %?2% or p.descrizione like %?2%) and p.idCompratore is null order by p.miPiace")
	List<Prodotto> findByRicerca(UUID user, String text);

	List<Prodotto> findByIdCompratore(UUID idCompratore);
}
