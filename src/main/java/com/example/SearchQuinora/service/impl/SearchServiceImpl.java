package com.example.SearchQuinora.service.impl;

import com.example.SearchQuinora.entity.Question;
import com.example.SearchQuinora.entity.User;
import com.example.SearchQuinora.repository.QuestionRepository;
import com.example.SearchQuinora.repository.UserRepository;
import com.example.SearchQuinora.searchrepo.SearchRepositoryQuestion;
import com.example.SearchQuinora.searchrepo.SearchRepositoryUser;
import com.example.SearchQuinora.service.SearchService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchRepositoryUser searchRepositoryUser;

    @Autowired
    private SearchRepositoryQuestion searchRepositoryQuestion;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public User saveDetailsUser(User user)
    {
        User user1 = userRepository.save(user);
        return searchRepositoryUser.save(user1);
    }

    @Override
    public Question saveDetailsQuestion(Question question)
    {
        Question question1 = questionRepository.save(question);
        return searchRepositoryQuestion.save(question1);
    }

    @Override
    public List<User> findUser(String text)
    {
        text = text.replace(" ","+");
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.queryStringQuery(text)
        .lenient(true).field("username").field("firstName").field("lastName")).should(QueryBuilders.queryStringQuery("*" + text + "*")
        .lenient(true).field("username").field("firstName").field("lastName"));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
        List<User> users = elasticsearchRestTemplate.queryForList(nativeSearchQuery, User.class, IndexCoordinates.of("usertest11"));
        return users;
    }

    @Override
    public List<Question> findQuestion(String text)
    {
        text = text.replace(" ","+");
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.queryStringQuery(text)
        .lenient(true).field("questionTitle").field("questionText").field("category"))
                .should(QueryBuilders.queryStringQuery("*" + text + "*").lenient(true).field("questionTitle")
                .field("questionText").field("category"));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
        List<Question> questions = elasticsearchRestTemplate.queryForList(nativeSearchQuery, Question.class, IndexCoordinates.of("questiontest1"));
        return questions;
    }
}
