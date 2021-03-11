package com.example.SearchQuinora.searchrepo;

import com.example.SearchQuinora.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;

public interface SearchRepositoryUser extends ElasticsearchRepository<User, Integer> {
}
