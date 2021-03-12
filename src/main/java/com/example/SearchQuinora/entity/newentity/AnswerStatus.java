package com.example.SearchQuinora.entity.newentity;

import lombok.Data;

@Data
public class AnswerStatus {

    private int Id;
    private Long questionID;
    private Boolean status;
}
