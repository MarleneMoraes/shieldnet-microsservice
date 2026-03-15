package com.shieldnet.posts.domain.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shieldnet.posts.domain.model.Post;
import com.shieldnet.posts.domain.service.exception.ObjectNotFoundException;
import com.shieldnet.posts.infrastructure.persistence.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Instant minDate, Instant maxDate) {
		return repo.fullSearch(text, minDate, maxDate.plus(1, ChronoUnit.DAYS));
	}
}
