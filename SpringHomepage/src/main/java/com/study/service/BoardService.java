package com.study.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
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

	public Board insert(Board board) { // 반환형을 Board로 바꿔줌
		return boardRepository.save(board);
	}

	public Optional<Board> selectDetail(Long bno) {
		return boardRepository.findById(bno) // Optional<Board>로 변경됨
							 .map(board-> {
								  board.setCount(board.getCount() + 1);
								  return boardRepository.save(board);
							  });
	}

	public Board update(Board board) {
		// update시에는 영속성 안에 board의 정보가 들어있어야 한다
		Board rboard = boardRepository.findById(board.getBno()).get();
		rboard.setTitle(board.getTitle());
		rboard.setContent(board.getContent());
		
		return boardRepository.save(rboard); // 반환형 Board로 변환
	}

}
