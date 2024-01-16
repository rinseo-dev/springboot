package com.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	/*
	  mapping 할 때는 보통 url에 폴더 이름은 들어가지 않지만
	  security는 권한 구분이 필요하기 때문에 폴더명으로 나뉨
	 */
	@RequestMapping("/")
	public @ResponseBody String root() {
		return "Security Welcome"; // 첫 페이지는 어디로 가는게 아니라 글씨만 보이게
	}
	
	@RequestMapping("/guest/welcome")
	public String welcome3() {
		return "guest/welcome3";
	}
	
	@RequestMapping("/member/welcome")
	public String welcome2() {
		return "member/welcome2";
	}
	
	@RequestMapping("/admin/welcome")
	public String welcome1() {
		return "admin/welcome1";
	}
	
	//security에서는 GetMapping으로 함 / RequestMapping가능.
	@GetMapping("/loginForm")
	public String loginForm() {
		return "security/loginForm";
	}
	
}
