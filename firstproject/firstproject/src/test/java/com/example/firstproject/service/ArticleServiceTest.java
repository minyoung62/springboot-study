package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅된다
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Test
    void index() {
        // 예상
        Article a = new Article(1L, "aaa", "111");
        Article b = new Article(2L, "bbb", "111");
        Article c = new Article(3L, "ccc", "111");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 실제
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());

    }

    @Test
    void show_success() {
        // 예상
        Long id = 1L;
        Article expected = new Article(1L, "aaa", "111");

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected.toString(), article.toString());

    }

    @Test
    void show_fail_no_have_id_input() {
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected, article);


    }

    @Test
    @Transactional
    void create_성공__title과_content만_있는_dto_입력() {
        // 예상
        String title = "zzz";
        String contents = "111";
        ArticleForm dto = new ArticleForm(null, title, contents);
        Article expected = new Article(4L, "zzz", "111");

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패___id가_포함된_dto_입력(){
        // 예상
        String title = "zzz";
        String contents = "111";
        ArticleForm dto = new ArticleForm(4L, title, contents);
        Article expected = null;

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected, article);
    }
    @Test
    @Transactional
    void update_성공___존재하는_id와_title_contents가__있는_dto_입력(){
        // 예상
        Long id = 1L;
        String title = "aaa";
        String contents = "111";
        ArticleForm dto = new ArticleForm(id, title, contents);
        Article expected = new Article(id, title, contents);

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_실패___id만_있는_dto_입력(){
        // 예상
        Long id = -1L;
        String title = "qwer";
        String contents = "12313";
        ArticleForm dto = new ArticleForm(id, title, contents);
        Article expected = null;

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void delete_성공___존재하는_id_입력(){
        // 예상
        Long id = 1L;
        String title = "aaa";
        String contents = "111";
        Article expected = new Article(id, title, contents);

        // 실제
        Article article = articleService.delete(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void delete_실패___존재하지_않는_id_입력(){
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.delete(id);

        // 비교
        assertEquals(expected, article);
    }
}