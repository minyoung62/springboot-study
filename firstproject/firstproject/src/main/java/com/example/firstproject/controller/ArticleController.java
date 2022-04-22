package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.ArticleSearch;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import com.example.firstproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j // 로깅을 위한 어노테이션
@RequiredArgsConstructor
public class ArticleController {


    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        ArticleDto saved = articleService.create(form);

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);


        // 1: id로 데이터를 가져옴
        ArticleDto articleDto = articleService.show(id);

        List<CommentDto> commentDtos = commentService.comments(id);

        // 2: 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleDto);
        model.addAttribute("commentDtos", commentDtos);

        // 3: 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model, ArticleSearch articleSearch){
        // 1: 모든 Article을 가져온다
        log.info("Search"+articleSearch.getSearch());
        List<ArticleDto> articleDtos = articleService.index(articleSearch);

        // 2: 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleDtos);

        // 3: 뷰 페이지를 설정
        return "articles/index";
    }



    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        // 수정할 데이터를 가져오기
        ArticleDto articleDtos = articleService.show(id);


        // 모델에 데이터를 등록
        model.addAttribute("article", articleDtos);

        // 뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){

        ArticleDto target = articleService.update(form.getId(), form);

        // 3. 수정 결과 페이지로 Redirect
        return "redirect:/articles/" + target.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("delete");

        ArticleDto delete = articleService.delete(id);

        // 2. 대상을 삭제한다
        if (delete != null){
            rttr.addFlashAttribute("msg","delete completed");
        }

        // 3. redirect를 한다
        return "redirect:/articles";
    }
}
