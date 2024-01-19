package com.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
	// 이거 왜 Crud안하고 Jpa다는건지 궁금 -> Crud도 DB에쓰고지우고되지만 Jpa친화적인건 JpaRepo

}
