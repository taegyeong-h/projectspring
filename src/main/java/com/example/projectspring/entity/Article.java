package entity;


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
    @GeneratedValue  // 자동으로 생성해주는 에노테이션!
    private Long id;

    @Column
    private String title;

    @Column
    private String content;



    public Article() {

    }

}
