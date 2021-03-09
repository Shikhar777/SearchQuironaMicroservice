package com.example.SearchQuinora.searchrepo;

import com.example.SearchQuinora.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SearchRepository extends ElasticsearchRepository<User, Integer> {
}
