package com.example.SearchQuinora.repository;

import com.example.SearchQuinora.entity.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Integer> {

    @Query(value = "select * from answersearch a where a.id=?1 and a.questionid=?2", nativeQuery = true)
    Answer findByAnswerId(int Id, Long questionID);
}
