package com.example.SearchQuinora.searchrepo;

import com.example.SearchQuinora.entity.Answer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SearchRepositoryAnswer extends ElasticsearchRepository<Answer, Integer> {

}
