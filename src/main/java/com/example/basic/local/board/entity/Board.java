package com.example.basic.local.board.entity;

import com.example.basic.global.common.entity.DateEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Schema(description = "게시판")
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board extends DateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false, length = 100)
    private String name;
}
