package com.shieldnet.posts.infrastructure.config;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.shieldnet.posts.domain.model.Post;
import com.shieldnet.posts.domain.model.User;
import com.shieldnet.posts.infrastructure.persistence.PostRepository;
import com.shieldnet.posts.infrastructure.persistence.UserRepository;

import tools.jackson.databind.ObjectMapper;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ObjectMapper objectMapper;

    public DatabaseSeeder(UserRepository userRepository, PostRepository postRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        postRepository.deleteAll();

        InputStream inputStream = new ClassPathResource("data/seed-data.json").getInputStream();
        SeedData data = objectMapper.readValue(inputStream, SeedData.class);

        userRepository.saveAll(data.users());

        postRepository.saveAll(data.posts());

        System.out.println("ShieldNet database seeded from JSON successfully!");
    }
    
    private record SeedData(List<User> users, List<Post> posts) {}
}