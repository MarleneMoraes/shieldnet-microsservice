package com.shieldnet.posts.domain.dto;

import com.shieldnet.posts.domain.model.User;

public record UserDTO(String id, String name, String email) {
    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}