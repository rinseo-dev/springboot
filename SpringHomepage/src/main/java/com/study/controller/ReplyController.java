package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.domain.Reply;
import com.study.service.ReplyService;

@Controller
public class ReplyController {

	@Autowired
	ReplyService replyService;

	
	/*	@GetMapping("/replyList")
		public Optional<Reply> replyList(HttpSession session, Model model) {
			
			Optional<Reply> replys = replyService.getReplyListByBno(bno);
			return replys;
		}
		*/
	@PostMapping("/rinsert")
	@ResponseBody
	public String replyInsert(@RequestParam(value="bno") Long bno,
			 @RequestParam(value="name") String name, 
			 @RequestParam(value="content") String content, 
			 Reply reply
			 ) {
		
			reply.setRef(bno);
			reply.setContent(content);
			reply.setName(name);
			
		replyService.rinsert(reply);
		
		return "";
	}
}
