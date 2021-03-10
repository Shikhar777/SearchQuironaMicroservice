package com.example.SearchQuinora.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "answersearch")
@Document(indexName = "answertest1")
public class Answer {

    @Id
    @org.springframework.data.annotation.Id
    @GenericGenerator(name = "answer_id_seq", strategy = "increment")
    @GeneratedValue(generator = "answer_id_seq", strategy = GenerationType.AUTO)
    private int answerId;
    private Long questionId;
    private String userName;
    private Boolean status;
    private Date timeStamp;
    private String imgsrc;
    private String answerText;
}
