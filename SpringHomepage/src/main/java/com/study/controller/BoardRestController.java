package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.domain.Board;
import com.study.repository.BoardRepository;

@RestController
@RequestMapping("/rest")
public class BoardRestController {
	
	@Autowired
	BoardRepository boardRepository;
	/*
	@GetMapping("/boards")
	List<Board> boardAll(){
		return boardRepository.findAll();
	}
	*/
	
	// http://localhost:8080/rest/boards?title=DB에 존재하는제목
	// DB에 존재하면 조회가 가능하며, @RequestParam에 명시된 값이 지정되지 않으면 error발생
	@GetMapping("/boards")
	//List<Board> boardAll(@RequestParam("title") String title){
	//RequestParam에 1개값만 들어올때는 value없어도 됨.
	List<Board> boardAll(@RequestParam(value="title",required=false) String title,
						 @RequestParam(value="content",required=false) String content){
		/*
		 if(title == null)
			return boardRepository.findAll();
		else
			return boardRepository.findByTitle(title)
		 */
		
		// RequestParam으로 받는 값이 2개가 됨
		if(title == null && content == null)
			return boardRepository.findAll();
		else
			return boardRepository.findByTitleOrContent(title,content);
		//title에 값이 들어오지 않으면 모든걸 출력, 값이 들어오면 해당 값만 출력되게 if문
		// 루트/rest/boards를 입력하면 전체값을 보여줌
		
	}
}
