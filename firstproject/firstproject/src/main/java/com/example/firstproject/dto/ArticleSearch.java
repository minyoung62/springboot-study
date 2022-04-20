package com.example.firstproject.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Builder
@AllArgsConstructor
@Getter
public class ArticleSearch {

    private String search;

}
