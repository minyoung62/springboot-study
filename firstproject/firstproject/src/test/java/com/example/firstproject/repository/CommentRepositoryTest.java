package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA와 연동한 테스트
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 4L;

            // 실제 수행행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            Article article = new Article(4L, "movie?","commentgo");
            Comment a = new Comment(1L, article, "Park", "good");
            Comment b = new Comment(2L, article, "Kim", "goood");
            Comment c = new Comment(3L, article, "Choi", "goood");

            List<Comment> expected = Arrays.asList(a, b, c);

            // 검증증
            assertEquals(expected.toString(), comments.toString());
        }
        /* Case 2: 1번 게시글의 모든 댓글 조회*/
        {

        }
   }

    @Test
    void findByNickname() {
    }
}