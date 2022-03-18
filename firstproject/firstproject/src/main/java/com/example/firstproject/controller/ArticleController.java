package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해 놓은 객체를 가져다가 자동 연결! (그래서 초기화 할 필요없음)
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
//        System.out.println(form.toString()); -> 로깅기능으로 대체!
        log.info(form.toString());

        // 1. Dto를 Entity로 변환
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB안에 save
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);

        // 1: id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        // 2: 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);

        // 3: 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1: 모든 Article을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();

        // 2: 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        // 3: 뷰 페이지를 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        // 수정할 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터를 등록
        model.addAttribute("article", articleEntity);

        // 뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        // 1. DTO를 Entity로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2. Entity를 DB에 Save
        // 2-1 DB에서 기존 데이터를 가져온다
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2 기존 데이터에 값을 갱신한다!
        if (target != null){
            articleRepository.save(articleEntity);
        }

        // 3. 수정 결과 페이지로 Redirect
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("delete");

        // 1. 삭제 대상을 가져온다
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2. 대상을 삭제한다
        if (target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","delete completed");
        }

        // 3. redirect를 한다
        return "redirect:/articles";
    }
}
