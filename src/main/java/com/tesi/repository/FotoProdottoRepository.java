package com.tesi.repository;

import com.tesi.entity.FotoByteArray;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FotoProdottoRepository extends JpaRepository<FotoByteArray, Long> {
	List<FotoByteArray> findByIdProdotto(Long idProdotto);

	@Transactional
	@Modifying
	@Query("delete from FotoByteArray where idProdotto=?1")
	void deleteByIdProdotto(Long idProdotto);
}
