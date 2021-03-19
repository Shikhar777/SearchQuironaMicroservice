package com.example.SearchQuinora.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity(name = "answersearch")
@Document(indexName = "answertest103")
public class Answer {

    @Id
    @org.springframework.data.annotation.Id
    @GenericGenerator(name = "answer_id_seq", strategy = "increment")
    @GeneratedValue(generator = "answer_id_seq", strategy = GenerationType.AUTO)
    private int Id;
    private Long questionID;
    @NotNull
    private String userName;
    private Boolean status;
    private Date timeStamp;
    @Column(columnDefinition = "TEXT")
    private String imgsrc;
    @Column(columnDefinition = "TEXT")
    private String answerText;
}
