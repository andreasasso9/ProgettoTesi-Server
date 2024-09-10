package com.tesi.service;

import com.tesi.entity.likes.Id;
import com.tesi.entity.likes.Likes;
import com.tesi.repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesServiceImpl implements LikesService{
	private final LikesRepository likesRepository;

	@Autowired
	public LikesServiceImpl(LikesRepository likesRepository) {
		this.likesRepository = likesRepository;
	}

	@Override
	public void save(Likes like) {
		likesRepository.save(like);
	}

	@Override
	public void delete(Id id) {
		likesRepository.deleteById(id);
	}
}
