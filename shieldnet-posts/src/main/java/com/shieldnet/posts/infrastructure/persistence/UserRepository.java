package com.shieldnet.posts.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shieldnet.posts.domain.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
