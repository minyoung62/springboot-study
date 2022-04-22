package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.ArticleSearch;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.User;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service // 서비스 선언! (서비스 객체를 스프링부트에 생성)
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ArticleDto> index(ArticleSearch articleSearch) {
        return articleRepository.findByFilter(articleSearch).stream()
                .map(article -> ArticleDto.createArticleDto(article))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ArticleDto show(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        return ArticleDto.createArticleDto(article);
    }


    public ArticleDto create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }

        // user 확인
        User user = userService.getUserFromOAuth2();

        if (userRepository.getById(user.getId()) == null) {
            throw new IllegalArgumentException("can't create because of no authority");
        }

        article.userSetting(user);

        return ArticleDto.createArticleDto(articleRepository.save(article));
    }



    public ArticleDto update(Long id, ArticleForm dto) {
        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();

        // 2. 대상 엔티티 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // user 확인
        User user = userService.getUserFromOAuth2();

        if (!user.getId().equals(target.getUser().getId())) {
            throw new IllegalArgumentException("can't update because of no authority");
        }


        // 3. 잘못된 요청 처리(대상이 없거나, id가 다른경우)
        if (target == null || id != article.getId()){
            return null;
        }

        // 4. 업데이트 및 정상 응답(200)
        target.patch(article);
        Article updated = articleRepository.save(target);
        return  ArticleDto.createArticleDto(updated);
    }

    public ArticleDto delete(Long id) {
        // 1. 대상 엔티티 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // user 확인
        User user = userService.getUserFromOAuth2();

        // 2. 잘못된 요청 처리(대상이 없는 경우)
        if (target == null){
            return null;
        }

        if (!user.getId().equals(target.getUser().getId())) {
            throw new IllegalArgumentException("can't delete because of no authority");
        }


        // 3. 대상 삭제 후 응답 반환
        articleRepository.delete(target);
        return ArticleDto.createArticleDto(target);
    }

    public List<ArticleDto> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 entity 묶음으로 변환
         List<Article> articleList= dtos.stream()
                .map(dto -> dto.toEntity())
                .collect((Collectors.toList()));

        // entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("fail")
        );

        // 결과값 반환
        return articleList.stream()
                .map(article -> ArticleDto.createArticleDto(article))
                .collect(Collectors.toList());

    }
}
