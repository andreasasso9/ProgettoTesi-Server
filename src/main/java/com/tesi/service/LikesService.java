package com.tesi.service;

import com.tesi.entity.likes.Id;
import com.tesi.entity.likes.Likes;

public interface LikesService {
	void save(Likes like);
	void delete(Id id);
}
