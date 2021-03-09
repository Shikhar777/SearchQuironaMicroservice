package com.example.SearchQuinora.repository;

import com.example.SearchQuinora.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
