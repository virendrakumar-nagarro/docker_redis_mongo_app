package com.redis.controller;

import com.redis.model.User;
import com.redis.repository.UserDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/redis")
public class UserController {

    @Autowired
    private UserDBRepository repository;

    @PostMapping("/user")
    @CachePut(value = "user", key = "#user.id")
    public User addNewUser(@RequestBody User user) {
        return repository.save(user);
    }

    @GetMapping("/getUserById")
    @Cacheable(value = "user", key = "#id")
    public Optional<User> getUserById(@RequestParam String id) {
        return repository.findById(id);
    }

    @PostMapping("/update")
    @CachePut(value = "user", key = "#user.id")
    public User updateUser(@RequestBody User user) {
        return repository.save(user);
    }

    @GetMapping("/delete")
    @CacheEvict(value = "user", key = "#id")
    public String deleteUserById(@RequestParam String id) {
        repository.deleteById(id);
        return "Successfully Deleted";
    }
}

