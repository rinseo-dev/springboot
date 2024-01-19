package com.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.study.domain.Member;
import com.study.repository.MemberRepository;

@Service
public class MemberService {
	// Service에서 Repository로 의존주입을 해야 검색해서 사용 가능함
	@Autowired
	private MemberRepository memberRepository;

	// List로 반환됨
	public List<Member> selectMembers1(String name) {
		return memberRepository.findMembers(name);
	}

	// 매개변수 차이가 있어서 같은 이름으로 오버로딩 가능. 같은 이름으로 매개변수가 다른 메소드가 생김.
	public List<Member>  selectMembers2(String name, Sort sort) {
		return memberRepository.findMembers(name, sort);
	}

	public Page<Member> selectMembers3(String name, Pageable pageable) {
		return memberRepository.findMembers(name,pageable);
	}

	// 자료형 개수가 같아서 오버로딩 못하므로 새로운 이름으로 만듦 findMembersNative 
	public List<Member> selectMembers4(String name, Sort sort) {
		return memberRepository.findMembersNative(name); // Repository에서 sort치워버려서 여기도 치움 
	}

	
}
