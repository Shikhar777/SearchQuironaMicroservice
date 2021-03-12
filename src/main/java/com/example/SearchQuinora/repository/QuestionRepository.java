package com.example.SearchQuinora.repository;

import com.example.SearchQuinora.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

    @Query(value = "select * from questionsearch q where q.question_id=?1", nativeQuery = true)
    Question findByQuestionId(int questionId);

}
