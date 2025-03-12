package com.example.projectspring.api;

import com.example.projectspring.dto.ArticleForm;
import com.example.projectspring.entity.Article;
import com.example.projectspring.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RestAPI용 컨트롤러! 데이터(JSON)를 반환
@RestController
public class ArticleApiController {

    @Autowired  // DI로 인해 스프링부트에서 떙겨와야 한다
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
       return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        System.out.println("articleRepository.findById(1L) = " + articleRepository.findById(1L));
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article entity = dto.toEntity();
        return articleRepository.save(entity);
    }
    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {

        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();

        // 2. 대상 엔티티를 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if(target == null || id != article.getId()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Article updated = articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //잘못된 요청 처리
        if(target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 대상 삭제
        articleRepository.delete(target);
        return  ResponseEntity.status(HttpStatus.OK).build();

    }

}
