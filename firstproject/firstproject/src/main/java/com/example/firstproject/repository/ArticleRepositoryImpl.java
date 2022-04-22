package com.example.firstproject.repository;

import com.example.firstproject.dto.ArticleSearch;
import com.example.firstproject.entity.Article;
//import com.example.firstproject.entity.QArticle;
import com.example.firstproject.entity.QArticle;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public List<Article> findByFilter(ArticleSearch articleSearch) {
        QArticle article = QArticle.article;

        return query.select(article)
                .from(article)
                .where(
                        searchEq(articleSearch.getSearch())
                )
                .fetch();

    }

    private BooleanExpression searchEq(String search) {
        if (!StringUtils.hasText(search)) return null;
        return QArticle.article.title.contains(search);
    }
}
