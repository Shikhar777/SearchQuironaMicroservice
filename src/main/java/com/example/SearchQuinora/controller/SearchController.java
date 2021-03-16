package com.example.SearchQuinora.controller;

import com.example.SearchQuinora.entity.Answer;
import com.example.SearchQuinora.entity.Question;
import com.example.SearchQuinora.entity.User;
import com.example.SearchQuinora.service.SearchService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
//@CrossOrigin("*")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping(value = "/saveUser")
    public User saveDetails(@RequestBody User user)
    {
        return searchService.saveDetailsUser(user);
    }

    @GetMapping(value = "/name/{text}")
    public List<User> findUser(@PathVariable("text") String text)
    {
        return searchService.findUser(text);
    }

    @PostMapping(value = "/saveQuestion")
    public Question saveDetails(@RequestBody Question question)
    {
        return searchService.saveDetailsQuestion(question);
    }

    @GetMapping(value = "/question/{text}")
    public List<Question> findQuestion(@PathVariable("text") String text)
    {
        return searchService.findQuestion(text);
    }

    @PostMapping(value = "/saveAnswer")
    public Answer saveDetails(@RequestBody Answer answer)
    {
        return searchService.saveDetailsAnswer(answer);
    }

    @GetMapping(value = "/answer/{text}")
    public List<Answer> findAnswer(@PathVariable("text") String text)
    {
        return searchService.findAnswer(text);
    }

}
