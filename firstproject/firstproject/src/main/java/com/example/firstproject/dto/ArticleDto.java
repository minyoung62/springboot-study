package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDto {

    private Long id;
    private String title;
    private String contents;
    private String userNickname;

    public static ArticleDto createArticleDto(Article article) {

        return ArticleDto.builder()
                .title(article.getTitle())
                .contents(article.getContents())
                .userNickname(article.getUser().getNickname())
                .id(article.getId())
                .build();
    }
}
