package com.example.SearchQuinora.service.impl;

import com.example.SearchQuinora.entity.Answer;
import com.example.SearchQuinora.entity.Question;
import com.example.SearchQuinora.entity.User;
import com.example.SearchQuinora.repository.AnswerRepository;
import com.example.SearchQuinora.repository.QuestionRepository;
import com.example.SearchQuinora.repository.UserRepository;
import com.example.SearchQuinora.searchrepo.SearchRepositoryAnswer;
import com.example.SearchQuinora.searchrepo.SearchRepositoryQuestion;
import com.example.SearchQuinora.searchrepo.SearchRepositoryUser;
import com.example.SearchQuinora.service.SearchService;
import org.apache.kafka.common.protocol.types.Field;
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
    private SearchRepositoryAnswer searchRepositoryAnswer;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

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
    public Answer saveDetailsAnswer(Answer answer)
    {
        Answer answer1 = answerRepository.save(answer);
        return searchRepositoryAnswer.save(answer1);
    }

    @Override
    public List<User> findUser(String text)
    {
        text = text.replace(" ","+");
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.queryStringQuery(text)
        .lenient(true).field("username").field("firstName").field("lastName").field("category").field("country").field("city")
        .field("state")).should(QueryBuilders.queryStringQuery("*" + text + "*")
        .lenient(true).field("username").field("firstName").field("lastName").field("category").field("country").field("city")
                .field("state"));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
        List<User> users = elasticsearchRestTemplate.queryForList(nativeSearchQuery, User.class, IndexCoordinates.of("usertest103"));
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
        List<Question> questions = elasticsearchRestTemplate.queryForList(nativeSearchQuery, Question.class, IndexCoordinates.of("questiontest103"));
        return questions;
    }

    @Override
    public List<Answer> findAnswer(String text)
    {
        text = text.replace(" ","+");
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.queryStringQuery(text)
        .lenient(true).field("answerText")).should(QueryBuilders.queryStringQuery("*" + text + "*").lenient(true)
        .field("answerText"));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
        List<Answer> answers = elasticsearchRestTemplate.queryForList(nativeSearchQuery, Answer.class, IndexCoordinates.of("answertest103"));
        return answers;
    }

}
