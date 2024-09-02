package com.tesi.repository;

import com.tesi.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
	Set<Token> findByUsername(String username);
}
