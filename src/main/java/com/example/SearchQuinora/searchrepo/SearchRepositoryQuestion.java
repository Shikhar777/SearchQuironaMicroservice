package com.example.SearchQuinora.searchrepo;

import com.example.SearchQuinora.entity.Question;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SearchRepositoryQuestion extends ElasticsearchRepository<Question, Integer> {
}
