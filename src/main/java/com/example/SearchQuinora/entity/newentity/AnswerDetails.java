package com.example.SearchQuinora.entity.newentity;

import lombok.Data;
import java.util.Date;

@Data
public class AnswerDetails {

    private int Id;
    private Long questionID;
    private String userName;
    private Boolean status;
    private Date timeStamp;
    private String imgsrc;
    private String answerText;
}
