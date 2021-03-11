package com.example.SearchQuinora.repository;

import com.example.SearchQuinora.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "select * from usersearch u where u.username=?1", nativeQuery = true)
    User findByUsername(String username);

}
