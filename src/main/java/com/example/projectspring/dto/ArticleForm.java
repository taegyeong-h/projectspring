package com.example.projectspring.dto;

import entity.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private Long id;           // edit.mustache의 요청 으로 인해 id 필드 추가
    private String title;
    private String content;

    public Article toEntity() {
       return new Article(id, title, content);   // Entity 객체
    }


}
