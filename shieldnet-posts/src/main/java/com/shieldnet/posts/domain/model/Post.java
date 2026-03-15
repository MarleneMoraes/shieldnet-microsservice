package com.shieldnet.posts.domain.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.shieldnet.posts.domain.dto.CommentDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class Post {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    
    private Instant date;
    private String title;
    private String body;
    private Author author;
    
    private List<CommentDTO> comments = new ArrayList<>();
}