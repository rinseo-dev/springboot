package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		// ResponseBody를 사용하면 문자열로 연결해줌.
		return "JSP로 실행";
	}
	
	@RequestMapping("/test1")
	public String test1() {
		
		return "test1"; // test1.jsp를 열라는게 됨/WEB-INF/views/test1.jsp
	}
	
	@RequestMapping("/test2")
	public String test2() {
		
		return "sub/test2"; // test1.jsp를 열라는게 됨/WEB-INF/views/test1.jsp
	}
}
