package com.example.poc.demo.poc.repository;

import com.example.poc.demo.poc.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    User deleteUserByEmail(String email);
}
