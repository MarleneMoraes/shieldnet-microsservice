package com.shieldnet.posts.domain.dto;

import java.time.Instant;
import com.shieldnet.posts.domain.model.Author;

public record CommentDTO(String text, Instant date, Author author) { }