package com.example.SearchQuinora.service;

import com.example.SearchQuinora.entity.Answer;
import com.example.SearchQuinora.entity.Question;
import com.example.SearchQuinora.entity.User;
import org.apache.kafka.common.protocol.types.Field;

import java.util.List;

public interface SearchService {

    User saveDetailsUser(User user);
    Question saveDetailsQuestion(Question question);
    Answer saveDetailsAnswer(Answer answer);
    List<User> findUser(String text);
    List<Question> findQuestion(String text);
    List<Answer> findAnswer(String text);
}
