package com.example.SearchQuinora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.SearchQuinora.repository")
@EnableElasticsearchRepositories(basePackages = "com.example.SearchQuinora.searchrepo")
public class SearchQuinoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchQuinoraApplication.class, args);
	}

}
