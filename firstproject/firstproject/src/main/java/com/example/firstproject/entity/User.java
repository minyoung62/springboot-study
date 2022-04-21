package com.example.firstproject.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Data
public class User {
    public User(Long id, String email, String nickname, OAuthList oauth) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.oauth = oauth;
//        this.timeStamp = timeStamp;
    }

    @Id @Column(name = "user_id") @NotNull
    private Long id;
    private String email;
    private String nickname;
    private String thumbnail;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Article> articles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private OAuthList oauth;
//
//    @Enumerated
//    private TimeStamp timeStamp;


}
