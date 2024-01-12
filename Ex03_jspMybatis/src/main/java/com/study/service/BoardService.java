package com.study.service;

import java.util.List;

import com.study.dto.Board;

public interface BoardService {
	List<Board> list();
	Board detailBoard(String no);
	int totalRecord();
	int insertBoard(Board board);
	int deleteBoard(String no);
	
}
