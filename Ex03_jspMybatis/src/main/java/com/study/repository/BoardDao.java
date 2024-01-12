package com.study.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.dto.Board;

// sql문(xml)과 메소드를 연결하고 결과값을 정의해놓은 타입으로 매핑 시켜주는 것
@Mapper
public interface BoardDao {
	// service에 있는걸 사용할 것
	List<Board> list();
	Board detailBoard(String no);
	int totalRecord();
	int insertBoard(Board board);
	int deleteBoard(String no);
}
