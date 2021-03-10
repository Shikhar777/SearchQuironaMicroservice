package com.example.SearchQuinora.service.impl;

import com.example.SearchQuinora.dto.UserRequestDto;
import com.example.SearchQuinora.dto.UserResponseDto;
import com.example.SearchQuinora.entity.User;
import com.example.SearchQuinora.repository.UserRepository;
import com.example.SearchQuinora.searchrepo.SearchRepository;
import com.example.SearchQuinora.service.SearchService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
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
    private SearchRepository searchRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveDetails(User user)
    {
        User user1 = userRepository.save(user);
        return searchRepository.save(user1);
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
}
