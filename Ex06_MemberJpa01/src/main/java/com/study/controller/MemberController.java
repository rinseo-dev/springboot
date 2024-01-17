package com.study.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.domain.Member;
import com.study.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/")
	public String root() throws Exception {
		return "menu";
	}
	
	@GetMapping("/insert")
	public String insert(@RequestParam("username") String username, Model model) { // 기본형 value="username" 1개라 생략
		/*
		 * Member member = new Member();
		 * member.setUsername(username);
		 * member.setCreateDate(LocalDate.now());
		 * 이렇게 생성자를 통해 작성했던걸 builder를 사용하면 가능
		 */
		
		// 생성자보다 가독성이 좋음
		// 나에게 맞는 생성자를 만드는 용도로 사용 	
		Member member = Member.builder()
						.username(username)
						.createDate(LocalDate.now())
						.build();
		Member result = memberService.insert(member);

		/*
		 * memberService.insert(member);를 먼저 생성하고
		 * Service에 insert(member)가 생성되고 나면
		 * Member에 memberService.insert(member)를 담을 수 있음
		 * result변수에 넣어줌
		 */
		//insert매개변수(?)에 Model추가
		model.addAttribute("member", result);
		
		return "insert";
	}
	
	@GetMapping("/select")
	public String select(@RequestParam("id") Long id,Model model) {
		// jsp에서 넘겨준 id 활용 / 알아서 Long형으로 변환..?
		Optional<Member> result = memberService.select(id); // Service에 자동생성
		// Service에서 넘어온 Optional<Member>에 넣어줌
		
		// Wrapping된 Member를 사용하는 방법
		/* 방법1
		 * result.getUsername을 사용할 수 없음.
		 * Member에 Wrapper를 하나 더 씌운 상태기때문에 벗겨서 사용해야함
	 	 * result.get().getUsername(); // get()으로 벗겨서 다시 가져옴
		 * result.get().getId(); 
		 */
		/* 방법2
		 * Member member = memberService.select(id).get();
		 * 이렇게 먼저 get으로 벗겨서 가져온 경우에는
		 * model.addAttribute("m",member);이렇게 member로 바로 받을 수 있음
		 */
		
		/*
		 * 방법3
		 * model.addAttribute("member",result.get());
		 * 여기서 벗겨서 사용하는 방법
		 */
		/*
		 * 방법4
		 * jsp에서 가져올 때 아이디 : ${member.id.get()}
		 */
		// .isPresent() : 데이터가 있는지 없는지 체크 - 있으면 true, 없으면 false
		if(result.isPresent()) {
			model.addAttribute("member",result.get()); // get()으로 벗겨서 Member형으로 넘겨줌
		}else {
			model.addAttribute("member", null);
			// member에 값이 없으면 null을 넣게 됨
		}
		return "select";
	}
	
	@GetMapping("/selectAll")
	public String selectAll(Model model) {
		/*
		 * List<Member> result = memberService.selectAll();
		 * model.addAttribute(result);
		 * 한 줄로 줄임
		 */
		model.addAttribute("members",memberService.selectAll());
		
		return "selectAll";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id) {
		memberService.delete(id);
		return "redirect:selectAll";
	}
	
	@GetMapping("/update")
	public String update(Member member, Model model) {
		// Entity이름과 같으면 바로 사용 가능 / 넘겨줄 값이 있으므로 Model사용
		/*memberService.update(member);이 값을 model에 담아서 넘길것*/
		model.addAttribute("member",memberService.update(member));
		return "update";
		
	}
}
