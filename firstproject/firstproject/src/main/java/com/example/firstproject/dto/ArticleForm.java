package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Builder
@Getter
public class ArticleForm {

    private Long id;
    private String title;
    private String contents;


    public Article toEntity(){
        return Article
                .builder()
                .id(this.id)
                .contents(this.contents)
                .title(this.title)
                .build();
    }
}
