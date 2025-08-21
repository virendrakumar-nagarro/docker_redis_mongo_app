package com.redis.repository;

import com.redis.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDBRepository extends MongoRepository<User, String> {
}
