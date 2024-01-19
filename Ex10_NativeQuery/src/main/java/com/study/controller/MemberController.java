package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@GetMapping("/selectByNameLike1")
	public String selectMembers1(@RequestParam("name") String search, Model model) {
		String name = search + "%";
		model.addAttribute("members",memberService.selectMembers1(name));
		
		return "select_name_list1";
	}
	
	@GetMapping("/selectByNameLike2")
	public String selectMembers2(@RequestParam("name") String search, Model model) {
		String name = search + "%";
		Sort sort = Sort.by(Sort.Order.asc("id"));
		model.addAttribute("members",memberService.selectMembers2(name, sort));
		
		return "select_name_list1";
	}

	@GetMapping("/selectByNameLike3")
	public String selectMembers3(@RequestParam("name") String search,
								   @RequestParam("page") int page,
								   Model model) {
		/*
		 * 앞에서 name, page가 넘어오는데 실제 Entity에는 name은 있지만 page는 없음
		 * DTO를 사용한다면 Entity는 놔두고 DTO에 page필드를 추가해서 값을 받으면 됨
		 * 그렇게 하지 않는 경우 @RequestParam으로 값을 가져와서 사용함
		 * 
		 * Model은 값을 이용하기 위해서 사용
		 */
	
		String name = search + "%";
		Sort sort = Sort.by(Sort.Order.desc("name")); // name 내림차순으로 sort
		// JPA에서 paging 1페이지는 0부터 시작 <-정해져있음 지켜줘야함
		int nPage = page - 1;
		
		// api로 만들어져있는 domain.Pageable을 import
		Pageable pageable = PageRequest.ofSize(10).withPage(nPage).withSort(sort);
		
		/*
		 * ofSize() : 한페이지에 몇개씩 보여주고 싶은지 작성
		 * .withPage(nPage) : PageRequest에 페이지번호를 설정
		 * .withSort(sort): 생성된 PageRequest에 정렬 정보를 설정
		 */
		Page<Member> result = memberService.selectMembers3(name,pageable);
		List<Member> content = result.getContent(); // 실제 객체가 담긴 List(내용물은 content)
		long totalElements = result.getTotalElements(); // 토탈 content개수
		int totalPages = result.getTotalPages(); // 토탈 페이지 수
		int size = result.getSize(); // 1page당 보여줄 개수
		int pageNumber = result.getNumber()+1; // 현재 페이지 0부터 시작 - 1페이지 시작 위해서 +1
		int numberOfElements = result.getNumberOfElements(); // 현재 페이지의 content개수
		
		model.addAttribute("members", content);
		model.addAttribute("totalElements", totalElements);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("numberOfElements", numberOfElements);
		
		return "select_name_list2";
	}
	
	@GetMapping("/selectByNameLike4")
	public String selectMembers4(@RequestParam("name") String search, Model model) {
		String name = search + "%";
		Sort sort = Sort.by(Sort.Order.asc("id"));
		model.addAttribute("members",memberService.selectMembers4(name, sort));
		
		return "select_name_list1";
	}
}
