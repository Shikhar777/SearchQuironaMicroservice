package com.example.SearchQuinora.service.impl;

import com.example.SearchQuinora.entity.Answer;
import com.example.SearchQuinora.entity.Question;
import com.example.SearchQuinora.entity.User;
import com.example.SearchQuinora.entity.newentity.*;

import com.example.SearchQuinora.repository.AnswerRepository;
import com.example.SearchQuinora.repository.QuestionRepository;
import com.example.SearchQuinora.repository.UserRepository;
import com.example.SearchQuinora.service.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class Consumer {

    @Autowired
    private SearchService searchService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    //com.example.SearchQuinora.update
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics="updateByUserToSearchAfterUpdate", groupId = "group_quora")
    public void listener1(String userDetailsFromUser)
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
        userSearch.setEmail(user.getEmail());
        userSearch.setUsername(user.getUsername());
        userSearch.setCategory(user.getCategory());
        //user.setUserId(user1.getUserId());
        searchService.saveDetailsUser(userSearch);
    }

    @KafkaListener(topics = "updateSearchQA", groupId = "group_quora")
    public void listener2(String questionDetails)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        QuestionDetails questionDetails1 = null;
        try{
            questionDetails1 = objectMapper.readValue(questionDetails, QuestionDetails.class);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Question: "+questionDetails1);
        Question question = new Question();
        question.setCategory(questionDetails1.getCategory());
        question.setQuestionText(questionDetails1.getQuestionText());
        question.setQuestionTitle(questionDetails1.getQuestionTitle());
        question.setQuestionId(questionDetails1.getQuestionId());
        question.setUsername(questionDetails1.getUsername());
        question.setStatus(questionDetails1.getStatus());
        question.setQuestionImage(questionDetails1.getQuestionImage());
        searchService.saveDetailsQuestion(question);
    }

    @KafkaListener(topics = "updateSearchAnswer", groupId = "group_quora")
    public void listener3(String answerDetails)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        AnswerDetails answerDetails1 = null;
        try{
            answerDetails1 = objectMapper.readValue(answerDetails, AnswerDetails.class);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Answer: "+answerDetails1);
        Answer answer = new Answer();
        answer.setId(answerDetails1.getId());
        answer.setAnswerText(answerDetails1.getAnswerText());
        answer.setQuestionID(answerDetails1.getQuestionID());
        answer.setTimeStamp(answerDetails1.getTimeStamp());
        answer.setImgsrc(answerDetails1.getImgsrc());
        answer.setStatus(answerDetails1.getStatus());
        answer.setUserName(answerDetails1.getUserName());
        searchService.saveDetailsAnswer(answer);
    }

    @KafkaListener(topics="afterUpdate", groupId = "group_quora")
    public void listener4(String userUpdateDetails)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        UserUpdateDetails userUpdateDetails1 = null;
        try{
            userUpdateDetails1 = objectMapper.readValue(userUpdateDetails, UserUpdateDetails.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("User: "+userUpdateDetails1);
        User userSearch = userRepository.findByUsername(userUpdateDetails1.getUsername());
        userSearch.setFirstName(userUpdateDetails1.getFirstName());
        userSearch.setLastName(userUpdateDetails1.getLastName());
        userSearch.setCountry(userUpdateDetails1.getCountry());
        userSearch.setCity(userUpdateDetails1.getCity());
        userSearch.setState(userUpdateDetails1.getState());
        userSearch.setBio(userUpdateDetails1.getBio());
        userSearch.setEducation(userUpdateDetails1.getEducation());
        userSearch.setEmployment(userUpdateDetails1.getEmployment());
        userSearch.setProfession(userUpdateDetails1.getProfession());
        userSearch.setProfileImage(userUpdateDetails1.getProfileImage());
        searchService.saveDetailsUser(userSearch);
    }

    @KafkaListener(topics="disableQuestion", groupId = "group_quora")
    public void listener5(String questionStatus)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        QuestionStatus questionStatus1 = null;
        try{
            questionStatus1 = objectMapper.readValue(questionStatus, QuestionStatus.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Update: "+questionStatus1);
        Question question = questionRepository.findByQuestionId(questionStatus1.getQuestionId());
        question.setStatus(questionStatus1.getStatus());
        searchService.saveDetailsQuestion(question);
    }

    @KafkaListener(topics="disableAnswer", groupId = "group_quora")
    public void listener6(String answerStatus)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        AnswerStatus answerStatus1 = null;
        try{
            answerStatus1 = objectMapper.readValue(answerStatus, AnswerStatus.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Update: "+answerStatus1);
        Answer answer = answerRepository.findByAnswerId(answerStatus1.getId(), answerStatus1.getQuestionID());
        answer.setStatus(answerStatus1.getStatus());
        searchService.saveDetailsAnswer(answer);
    }

}
