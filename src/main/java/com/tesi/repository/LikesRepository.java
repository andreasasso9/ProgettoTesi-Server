package com.tesi.repository;

import com.tesi.entity.likes.Id;
import com.tesi.entity.likes.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Id> {
}
