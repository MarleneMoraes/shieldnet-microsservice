package com.shieldnet.posts.api;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shieldnet.posts.domain.model.Post;
import com.shieldnet.posts.domain.service.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostController {

	private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @GetMapping(value="/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
        String decodedText = URLDecoder.decode(text, StandardCharsets.UTF_8);
        List<Post> list = service.findByTitle(decodedText);
        return ResponseEntity.ok().body(list);
    }

	@GetMapping(value="/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
	        @RequestParam(value="text", defaultValue="") String text,
	        @RequestParam(value="minDate", defaultValue="") 
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant minDate,
	        @RequestParam(value="maxDate", defaultValue="") 
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant maxDate) {

	    Instant min = (minDate == null) ? Instant.EPOCH : minDate;
	    Instant max = (maxDate == null) ? Instant.now() : maxDate;

	    List<Post> list = service.fullSearch(text, min, max);
	    return ResponseEntity.ok().body(list);
	}
}
