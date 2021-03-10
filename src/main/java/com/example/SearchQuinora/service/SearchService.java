package com.example.SearchQuinora.service;

import com.example.SearchQuinora.entity.Question;
import com.example.SearchQuinora.entity.User;

import java.util.List;

public interface SearchService {

    User saveDetailsUser(User user);
    Question saveDetailsQuestion(Question question);
    List<User> findUser(String text);
    List<Question> findQuestion(String text);
}
