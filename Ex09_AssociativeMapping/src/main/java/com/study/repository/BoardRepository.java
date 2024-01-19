package com.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{ // Entity, @Id의 자료형

}
