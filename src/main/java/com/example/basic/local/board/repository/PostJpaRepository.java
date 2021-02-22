package com.example.basic.local.board.repository;

import com.example.basic.local.board.entity.Board;
import com.example.basic.local.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

    /*
     * 주의 : join 테이블의 조건을 줄 때는 컬럼명이 아니라 객체자체를 인자로 주입해야 함.
     */
    List<Post> findByBoardOrderByPostIdDesc(Board board);
}
