package com.shieldnet.posts.domain.dto;

import java.time.Instant;

public record PostResponse(
    String id, 
    Instant date, 
    String title, 
    String body, 
    String authorName
) { }