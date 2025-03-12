package com.example.projectspring.controller;

import com.example.projectspring.dto.ArticleForm;
import com.example.projectspring.repository.ArticleRepository;
import com.example.projectspring.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@Slf4j  // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired  // 스프링 부트가 미리 생성해 놓은 객체를 가져다가 자동으로 연결! DI
    private ArticleRepository articleRepository;


    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";

    }

    // templates/artlcles/new.mustache ->  action="/articles/create" method="post"
    // dto/ArticleForm   -> form 데이터를 받아올 그릇 (title, content)
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info("form: {}", form);
        // DTO -> Entity로 변환
        Article article = form.toEntity();
        log.info("article: {}", article);

        // Repository에게 Entity를 DB안에 저장
        Article saved = articleRepository.save(article);
        log.info("saved: {}", saved);
        return "redirect:/articles/" + saved.getId();        // "redirect:/articles/{id}" x   "redirect:/articles/" + saved.getId() O
    }


    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model, ModelMap modelMap) {
        log.info("id: {}", id);
        // 1. id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지 설정
        return "/articles/show";

    }

    @GetMapping("/articles")
    public String index(Model model) {

        // 모든 article을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();

        // 모든 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        // 뷰 페이지 설정
        return "/articles/index";
    }

// 수정 폼 만들기
    @GetMapping("articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);   // DB에 데이터를 꺼내옴
        // 모델에 데이터 등록
        model.addAttribute("article", articleEntity); //
        // 뷰 페이지 설정
        return "articles/edit";
    }

// 수정 페이지의 데이터를 DB로 갱신하고, 확인
    @PostMapping("/articles/update")
    public String update(ArticleForm form) {  // DTO로 받음
        log.info("entity: {}", form);
        // 1.dto를 -> Entity로 변환
        Article entityEntity = form.toEntity();
        log.info("entity: {}", entityEntity.toString());
        //2. Entity -> DB로 저장
        //2-1: 기존의 것을 바꾸므로 DB에 기존 데이터를 가져온다
        Article target = articleRepository.findById(entityEntity.getId()).orElse(null);

        // 2-2 기존 데이터의 값을 갱신한다!
        if(target != null) {
            articleRepository.save(entityEntity);
        }
        // 3. 수정 결과를 페이지로 리다이렉트 한다\
        return "redirect:/articles/" + entityEntity.getId() ;
    }

    // Delete
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다.");
        // 1. 삭제 대상을 가져온다.
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2. 대상을 삭제한다.
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료 되었습니다.");
        }
        // 3. 결과 페이지를 삭제한다.

        return "redirect:/articles/";
    }

}


