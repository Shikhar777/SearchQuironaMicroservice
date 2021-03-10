package com.example.SearchQuinora.repository;

import com.example.SearchQuinora.entity.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
}
