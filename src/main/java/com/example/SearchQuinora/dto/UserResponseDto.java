package com.example.SearchQuinora.dto;

import com.example.SearchQuinora.entity.Education;
import com.example.SearchQuinora.entity.Employment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponseDto {

    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String profileCredential;
    private String address;
    //private LocalDateTime joiningDate;
    private String bio;
    //private String profileImage;

    private List<Education> education;
    private List<Employment> employment;
}
