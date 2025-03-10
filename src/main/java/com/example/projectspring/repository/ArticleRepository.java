package com.example.projectspring.repository;

import entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {


    @Override
    List<Article> findAll();
}
