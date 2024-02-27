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
	/*
	 * @ResponseBody
	 * 컨트롤러 메서드가 HTTP 응답의 본문(body)으로 데이터를 직접 반환하도록 지정하는 어노테이션
	 * 메서드가 반환하는 값이 View Resolver를 통해 HTML 페이지가 아닌 직접 HTTP 응답의 body로 전송
	 * @ResponseBody 어노테이션은 checkId 메서드가 boolean 값을 반환하면 이 값을 HTTP 응답의 본문으로 직접 설정하겠다는 의미
	 * 
	 * /idCheck 엔드포인트에 GET 요청을 보내면 해당 메서드는 memberService.idCheck(id)를 호출하여 아이디의 유효성을 확인하고,
	 * 그 결과인 boolean 값을 바로 HTTP 응답의 본문으로 반환
	 */
	
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
			/*
			 * member테이블에 있는 password값과 사용자가 입력한 password값을
			 * pEncoder를 통해 비교했을 때, 동일하다면 "loginUser"정보를 model에 넣음?
			 */
			
			// if문이 참이면 model을 통해 "loginUser"를 가지고 감 - requestScope인 상태
			// 로그인 내내 가져가려면 "loginUser"키를 sessionScope로 변환해야 함.
			// @SessionAttributes({"loginUser"})어노테이션 달아줌
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		if(!status.isComplete()) { // status가 complete가 아니면
			// .isComplete()는 세션이 완료되었는지 여부를 반환함. 현재 세션의 상태 확인
			
			status.setComplete();
			// 세션이 완료되지 않았을 때 setComplete()를 호출해서 세선종료, 클리어 함
		}
		return "redirect:/"; // 세션 로그아웃이 완료된 상태로 리다이렉트됨
	}
}
