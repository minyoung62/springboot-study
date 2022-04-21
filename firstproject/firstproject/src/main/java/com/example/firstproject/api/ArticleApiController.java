package com.example.firstproject.api;


import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Slf4j
public class ArticleApiController {

    @Autowired // DI, 생성 객체를 가져와 연결
    private ArticleService articleService;

    // GET
    @GetMapping("/api/articles")
    public List<ArticleDto> index(){
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public ArticleDto index(@PathVariable Long id){
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleDto> create(@RequestBody ArticleForm dto) {
        ArticleDto created = articleService.create(dto);

        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<ArticleDto> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto){

        ArticleDto updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }


    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<ArticleDto> delete(@PathVariable Long id){
        ArticleDto deleted = articleService.delete(id);
        return (deleted != null ) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(deleted):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 트랜젝션 -> 실패 -> 롤백 !
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<ArticleDto>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<ArticleDto> createdList = articleService.createArticles(dtos);
        return (createdList != null)?
                ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

}
