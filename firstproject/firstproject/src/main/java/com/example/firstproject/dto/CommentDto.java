package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class CommentDto {
    private Long id;

    @JsonProperty("article_id")
    private Long articleId;
    private String nickname;
    @JsonProperty("user_id")
    private Long userId;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .articleId(comment.getArticle().getId())
                .nickname(comment.getNickname())
                .userId(comment.getUserId())
                .body(comment.getBody())
                .build();
    }
}
