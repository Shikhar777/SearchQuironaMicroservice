package com.example.SearchQuinora.entity.newentity;

import com.example.SearchQuinora.entity.Education;
import com.example.SearchQuinora.entity.Employment;
import lombok.Data;

import java.util.List;

@Data
public class UserUpdateDetails {

    private String firstName;
    private String lastName;
    private String username;
    private String profession;
    private String city;
    private String country;
    private String state;
    private String bio;
    private String profileImage;
    private List<Education> education;
    private List<Employment> employment;
}
