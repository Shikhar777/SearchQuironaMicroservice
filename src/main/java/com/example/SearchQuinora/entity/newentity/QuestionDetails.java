package com.example.SearchQuinora.entity.newentity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class QuestionDetails {

    //@Id
    //@org.springframework.data.annotation.Id
    //@GenericGenerator(name = "question_id_seq", strategy = "increment")
    //@GeneratedValue(generator = "question_id_seq", strategy = GenerationType.AUTO)
    private int questionId;
    //@NotBlank
    //@Size(min=10, max = 150)
    private String questionTitle;
    //@NotBlank
    //@Size(min = 30, max = 400)
    private String questionText;
    private String category;
    @CreatedDate
    private Date createdAt;
    private String username;
    private Boolean status;
    private String questionImage;

    @Override
    public String toString() {
        return "QuestionDetails{" +
                "questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionText='" + questionText + '\'' +
                ", category='" + category + '\'' +
                ", createdAt=" + createdAt +
                ", username='" + username + '\'' +
                ", status=" + status +
                '}';
    }
}
