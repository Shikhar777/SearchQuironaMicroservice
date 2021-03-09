package com.example.SearchQuinora.controller;

import com.example.SearchQuinora.entity.User;
import com.example.SearchQuinora.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping(value = "/save")
    public User saveDetails(@RequestBody User user)
    {
        return searchService.saveDetails(user);
    }

    @GetMapping(value = "/name/{text}")
    public List<User> findUser(@PathVariable("text") String text)
    {
        return searchService.findUser(text);
    }
}
