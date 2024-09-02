package com.tesi.repository;

import com.tesi.entity.FotoByteArray;
import com.tesi.entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FotoProdottoRepository extends JpaRepository<FotoByteArray, Long> {
	List<FotoByteArray> findByProdotto(Prodotto prodotto);

	@Transactional
	@Modifying
	@Query("delete from FotoByteArray where prodotto=?1")
	void deleteByProdotto(Prodotto prodotto);
}
