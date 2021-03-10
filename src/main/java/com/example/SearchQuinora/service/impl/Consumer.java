package com.example.SearchQuinora.service.impl;

import com.example.SearchQuinora.entity.User;
import com.example.SearchQuinora.entity.newentity.UserDetailsFromUser;

import com.example.SearchQuinora.service.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class Consumer {

    @Autowired
    private SearchService searchService;

    //com.example.SearchQuinora.update
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics="updateByUserToSearchAfterUpdate", groupId = "group_id")
    public void listener(String userDetailsFromUser)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDetailsFromUser user = null;
        try{
            user = objectMapper.readValue(userDetailsFromUser, UserDetailsFromUser.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("User: "+user);
        User userSearch = new User();
        userSearch.setFirstName(user.getFirstName());
        userSearch.setLastName(user.getLastName());
        userSearch.setAddress(user.getAddress());
        userSearch.setBio(user.getBio());
        userSearch.setEducation(user.getEducation());
        userSearch.setEmployment(user.getEmployment());
        userSearch.setProfileCredential(user.getProfileCredential());
        userSearch.setUsername(user.getUsername());
        //user.setUserId(user1.getUserId());
        searchService.saveDetailsUser(userSearch);
    }

}
