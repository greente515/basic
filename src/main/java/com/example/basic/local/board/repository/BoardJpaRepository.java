package com.example.basic.local.board.repository;

import com.example.basic.local.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {

    Board findByName(String name);
}
