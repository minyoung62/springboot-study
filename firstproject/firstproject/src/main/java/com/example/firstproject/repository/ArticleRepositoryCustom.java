package com.example.firstproject.repository;

import com.example.firstproject.dto.ArticleSearch;
import com.example.firstproject.entity.Article;

import java.util.List;

public interface ArticleRepositoryCustom {

    List<Article> findByFilter(ArticleSearch articleSearch);

}
