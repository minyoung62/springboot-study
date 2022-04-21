package com.example.firstproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // DB가 해당 객체를 인식 가능 (해당 테이블을 만든다)
@AllArgsConstructor
@NoArgsConstructor // 디폴트 생성자를 추가
@ToString
@Getter
@Builder
public class Article {

    @Id // 대표값을 지정! like a 주미등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성 어노테이션
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

//    @Embedded
//    private TimeStamp timeStamp;

    public void userSetting(User user) {
        if(user == null) throw new IllegalArgumentException("해당유저가 존재하지 않음");
        this.user = user;
    }

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.contents != null)
            this.contents = article.contents;
    }
}
