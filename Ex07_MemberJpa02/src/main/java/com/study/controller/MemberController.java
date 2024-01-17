package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/")
	public String root() throws Exception{
		return "menu";
	}
	
	@GetMapping("/insert")
	public String insert() {
		memberService.insert();
		return "insert";
	}
	
	@GetMapping("/selectAll")
	public String selectAll(Model model) {
		model.addAttribute("members",memberService.selectAll());
		return "selectAll";
	}
	
	@GetMapping("/selectById")
	public String selectById(@RequestParam("id") Long id, Model model) {
		model.addAttribute("member", memberService.selectById(id).get());
		// member라는 이름으로 반환한 결과를 가져감 / Optional이므로 .get()으로 벗겨서 사용
		return "select_id";
	}
	
	@GetMapping("/selectByEmail")
	public String selectByEmail(@RequestParam("email") String email, Model model) {
		model.addAttribute("member",memberService.selectByEmail(email).get());
		return "select_email";
	}
	
	@GetMapping("/selectByName")
	public String selectByName(@RequestParam("name") String name, Model model) {
		model.addAttribute("member", memberService.selectByName(name).get());
		return "select_name";
	}
	
	@GetMapping("/selectByNameLike")
	public String selectByNameLike(@RequestParam("name") String search, Model model) {
		String name = search+"%"; // name=search% / 검색어를 포함하는걸 찾으려면 이렇게name=%search%
		
		model.addAttribute("members", memberService.selectByNameLike(name));
		return "select_name_like";
	}
	
	@GetMapping("/selectByNameLikeDesc")
	public String selectByNameLikeDesc(@RequestParam("name") String search, Model model) {
		String name = search + "%";
		model.addAttribute("members",memberService.selectByNameLikeDesc(name));
		return "select_name_like";
	}
	
	@GetMapping("/selectByNameLikeSort")
	public String selectByNameLikeSort(@RequestParam("name") String search,Model model) {
		String name = search + "%";
		Sort sort = Sort.by(Sort.Order.desc("name"));
	
		model.addAttribute("members", memberService.selectByNameLike(name,sort));
		// 매개변수가 2개인 것이 생겼으므로 오버로딩 필요
		
		return "select_name_like";
	}
}
