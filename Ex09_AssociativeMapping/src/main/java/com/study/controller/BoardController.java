package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.domain.Board;
import com.study.domain.Member;
import com.study.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@PostMapping("/binsert")
	public String insert(Board board, Model model) {
		// Member에서 얻어와서 사용해야함 - 로그인 기능이 있다면 session에서 가져오면됨		
		/* 
		 * bno은 자동, title, content, writer를 넘겨줘야함
		 * form에서 title, content는 이미 넘어왔으므로 writer값이 필요해서 Member에서 가져옴
		 */
		
		// FK로 걸어놓은 경우 객체로 넘겨줘야하고, 객체로 받아와야함.
		Member m = new Member();
		m.setId("user01"); // writer는 member에서 가져와서 넣어줘야함 - membe객체로
		
		board.setMember(m); // 멤버객체로 넘겨줘야함.
		/*
		 * board.setMember("user01"); 이렇게 직접 작성하면 오류가 발생함
		 * board.setWriter("user01"); 이건 데이터베이스의 필드이름이므로 역시 오류
		 */
		
		/*
		 * board에는 writer가 있고, 테이블에 넘기면 field명은 writer
		 * 실제 DB에는  | bno | title | content | writer| 가 존재하고
		 * wirter에 들어가는 이름이기 때문에 member객체를 가지고 넣어줘야함
		 * member객체의 id가 writer에 들어가게됨 
		 * 그럼 DB에 객체없이 writer에 user01이라는 id가 들어가게됨.
		 * 값을 받아올때도 member객체로 받아와야함
		 */
		
		Board result = boardService.insert(board); // 받아온 값을 result에 넣음
		model.addAttribute("board", result);
		
		result.getMember().getId(); // Member객체로 가져오게 함
		System.out.println("writer:"+result.getMember().getId());
		
		return "binsert";
		
	}
}
