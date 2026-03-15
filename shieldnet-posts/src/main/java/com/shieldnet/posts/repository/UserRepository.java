package com.shieldnet.posts.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shieldnet.posts.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
