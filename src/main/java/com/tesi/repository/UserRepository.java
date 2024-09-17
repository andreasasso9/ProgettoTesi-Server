package com.tesi.repository;

import com.tesi.entity.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	User findByUsername(String username);
	User findByEmail(String email);

	@Query("select u from User u where u.id=?1")
	@Transactional
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	User findByIdForUpdate(UUID id);
}
