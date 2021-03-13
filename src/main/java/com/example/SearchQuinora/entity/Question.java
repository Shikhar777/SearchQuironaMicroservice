package com.example.SearchQuinora.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity(name = "questionsearch")
@Document(indexName = "questiontest7")
public class Question {

    @Id
    @org.springframework.data.annotation.Id
    @GenericGenerator(name = "question_id_seq", strategy = "increment")
    @GeneratedValue(generator = "question_id_seq", strategy = GenerationType.AUTO)
    private int questionId;
    @NotBlank
    @Size(min=10, max = 150)
    private String questionTitle;
    @NotBlank
    @Size(min = 30, max = 400)
    private String questionText;
    private String category;
    private String username;
    @Column(columnDefinition = "boolean default true")
    @NotNull
    private Boolean status;
    @Column(columnDefinition = "TEXT")
    private String questionImage;
}
