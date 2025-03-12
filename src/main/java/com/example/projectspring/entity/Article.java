package com.example.projectspring.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@ToString
@Getter
@Entity     // DB 에서 해당 객체를 인식 하기 위해 Entity 에노테이션 추가
public class Article {

    @Id // 대표값을 지정 ! Like a 주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // DB가 ID를 자동생성
    private Long id;

    @Column
    private String title;

    @Column
    private String content;



    public Article() {

    }

    public void patch(Article article) {
        if(article.title != null) {
            this.title = article.title;
        } if(article.content != null) {
            this.content = article.content;
        }
    }

}
