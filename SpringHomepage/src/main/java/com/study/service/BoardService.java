package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.study.domain.Board;
import com.study.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	BoardRepository boardRepository;

	// Page<Board> -> springframework로 import
	public Page<Board> list(Pageable pageable) {
		return boardRepository.findAll(pageable);
		// 알아서 @Id값으로 내림차순 페이징처리
		
	}
}
