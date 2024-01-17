package com.study.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.domain.Member;
import com.study.repository.MemberRepository;

@Service
public class MemberService {
	// Service에서 Repository로 의존주입을 해야 검색해서 사용 가능함
	@Autowired
	private MemberRepository memberRepository;

	public Member insert(Member member) { // 자동생성
		// save() : insert해주는 메소드 - 이미 만들어진 save()를 가져와서 사용하는것
		Member rMember = memberRepository.save(member);
		return rMember;
	}

	/*
	 * Optional<T> : NullpointerException을 방지하기 위해 사용
	 * 				 기존의 반환 값 타입 T에 Optional<T>를 Wrapping하여,
	 * 				 null 대신 Optional안에 Bean 타입 객체를 돌려 준다
	 *   ex) Member member = memberRepository.findById(id);
	 *   	-> 그 값이 없을 때
	 *   	   member.getUsername(); => 호출시 NullpointerException 발생
	 */
	public Optional<Member> select(Long id) {
		// findById() : Entity에서 @Id가 붙은 필드를 의미함
		Optional<Member> member = memberRepository.findById(id);
		// type이 member가 아니라 optional로 넘어옴
		// 강제로 Member member = memberRepository.findById(id);라고 하면
		// Optional<Member> member로 작성하라고 오류 발생함
		return member;
	}

	public List<Member> selectAll() { // 반환형 List<Member>해줘야함.
		List<Member> list = memberRepository.findAll();
		return list;
	}

	public void delete(Long id) { // 여긴 반환값 없어서 void
		memberRepository.deleteById(id);
		
	}

	public Member update(Member member) {
		//update()는 따로 존재하지 않고, insert를 해주면 덮어쓰기 하는 방식
		// .save()는 달라진 값만 insert됨. update처럼 사용가능
		// @Id필드의 값이 DB에 들어있으면 update, 없으면 insert해줌
		/* 
		 * Member rMember = memberRepository.save(member);
		 * return rMember;
		 * 아래 한줄로 작성 가능
		 */
		return memberRepository.save(member);
			
	}
}
