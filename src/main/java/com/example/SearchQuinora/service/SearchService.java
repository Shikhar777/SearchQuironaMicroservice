package com.example.SearchQuinora.service;

import com.example.SearchQuinora.entity.User;

import java.util.List;

public interface SearchService {

    User saveDetails(User user);
    List<User> findUser(String text);
}
