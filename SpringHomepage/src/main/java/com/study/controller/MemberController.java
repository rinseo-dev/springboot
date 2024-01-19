package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.study.domain.Member;
import com.study.service.MemberService;

@Controller
@SessionAttributes({"loginUser"}) // 여러개면 쉼표로 구분 / 중괄호여서 Attribute에 s붙여야함
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder pEncoder; // passwordEncoder()작성한 값으로 의존성 주입
	
	// root에서 index로
	@RequestMapping("/")
	public String root() throws Exception{
		return "index";
	}
	
	// 기본 index로 가게
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@GetMapping("/enrollForm")
	public String enrollForm() {
		return "member/enrollForm";
	}
	
	@GetMapping("/idCheck")
	@ResponseBody
	public boolean checkId(@RequestParam("id") String id) {
		return memberService.idCheck(id);
		// model에 담을 필요 없이 바로 return에 담아도 T/F값으로 넘어감
	}
	
	@PostMapping("/memberInsert")
	public String memberInsert(Member member) {
		/*
		 * String enPass = pEncoder.encode(member.getPassword());
		 * // encode들어온걸 암호화해서 변수에 넣음
		 * 
		 * //member에 있는 값 중에 password를 사용자가 입력한 값 말고 encode된걸로 바꿔야함
		 * member.setPassword(enPass); // 이렇게 하면 encode된걸 가져옴
		 * 두 줄을 아래에서 한줄로 바꿈
		 */

		member.setPassword(pEncoder.encode(member.getPassword()));
		// 가입한 DB찾아보면 비밀번호에 암호화된 값으로 저장됨
		
		memberService.insert(member);
		return "redirect:/"; // insert되면 루트로 감
	}
	
	@PostMapping("/login")
	public String login(Member member, Model model) { // @Param으로 아이디,비번 받아도 되고 Member로 한번에 받아도 됨
		Member loginUser = memberService.login(member);
		// service에서 .get()으로 wrap을 하나 벗겨서 가져왔기 때문에 Member로 사용
		
		// pEncoder.matches(member.getPassword(), loginUser.getPassword());
		/* 
		 * .matches(사용자가 넣은 PW,DB에서 가져온 PW)
		 * matches()메소드는 맞으면 true, 틀리면 false가 반환됨 - 이미 정의돼있음
		 */
		
		if(loginUser != null && 
				pEncoder.matches(member.getPassword(), loginUser.getPassword())) {
			model.addAttribute("loginUser", loginUser);
			// if문이 참이면 model을 통해 "loginUser"를 가지고 감 - requestScope인 상태
			// 로그인 내내 가져가려면 "loginUser"키를 sessionScope로 변환해야 함.
			// @SessionAttributes({"loginUser"})어노테이션 달아줌
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		if(!status.isComplete()) { // status가 complete가 아니면
			status.setComplete();
		}
		return "redirect:/";
	}
}
