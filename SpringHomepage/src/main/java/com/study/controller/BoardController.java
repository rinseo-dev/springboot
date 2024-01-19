package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/list")
	public String list(@RequestParam(value="nowPage", defaultValue="0") int nowPage, 
					   Model model) { //값을 넘겨야하니까 Model사용
		// 페이징값이 안들어왔을 때 무조건 첫번째 페이지가 오게 - int nowPage에 담음
			
									// PageRequest.of(현재페이지, 페이지당 개수[, sort]) 
		model.addAttribute("boardPage", boardService.list(PageRequest.of(nowPage, 10)));
		model.addAttribute("nowPage", nowPage);
		
		return "board/list";
	}
	
	@GetMapping("/insertForm")
	public String insertForm() {
		return "board/insertForm";
	}
}
