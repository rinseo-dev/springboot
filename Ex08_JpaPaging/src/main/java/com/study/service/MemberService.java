package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.study.domain.Member;
import com.study.repository.MemberRepository;

@Service
public class MemberService {
	// Service에서 Repository로 의존주입을 해야 검색해서 사용 가능함
	@Autowired
	private MemberRepository memberRepository;

	public Page<Member> selectByNameLike(String name, Pageable pageable) {
		Page<Member> member = memberRepository.findByNameLike(name, pageable);
		return member;
	}

	
}
