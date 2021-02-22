package com.example.basic.local.board.entity;

import com.example.basic.global.common.entity.DateEntity;
import com.example.basic.local.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Schema(description = "게시물")
@Entity
@Getter @Setter
@NoArgsConstructor
public class Post extends DateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board; //게시글-게시판의 관계 N:1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "msrl")
    private User user; //게시글-회원의 관계 N:1

    @JsonIgnore //join table 이 json 결과에 표시되지 않도록 처리
    public Board getBoard(){
        return board;
    }

    // 생성자
    public Post(User user, Board board, String author, String title, String content) {
        this.user = user;
        this.board = board;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    // 수정시 데이터 처리
    public Post setUpdate(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        return this;
    }
}
