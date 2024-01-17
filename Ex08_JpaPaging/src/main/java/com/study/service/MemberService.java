package com.study.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.study.domain.Member;
import com.study.repository.MemberRepository;

@Service
public class MemberService {
	// Service에서 Repository로 의존주입을 해야 검색해서 사용 가능함
	@Autowired
	private MemberRepository memberRepository;

	public void insert() {
		Member member;
		
		member = new Member("test1@test.com","이순신");
		memberRepository.save(member);
		member = new Member("test2@test.com","강감찬");
		memberRepository.save(member);
		member = new Member("test3@test.com","김유신");
		memberRepository.save(member);
		member = new Member("test4@test.com","연개소문");
		memberRepository.save(member);
		member = new Member("test5@test.com","세종대왕");
		memberRepository.save(member);
		member = new Member("test6@test.com","계백");
		memberRepository.save(member);
		member = new Member("test7@test.com","김춘추");
		memberRepository.save(member);
		member = new Member("test8@test.com","을지문덕");
		memberRepository.save(member);
		member = new Member("test9@test.com","이성계");
		memberRepository.save(member);
		
	}

	public List<Member> selectAll() {
		return memberRepository.findAll();

	}

	public Optional<Member> selectById(Long id) {
		return memberRepository.findById(id);
	}

	public Optional<Member> selectByEmail(String email) {
		return memberRepository.findByEmail(email);
		/* findById()는 존재하는 메소드를 가져와서 사용하는거고
		 * findByEmail()은 없는 메소드를 repository에 만들어서 사용해야 함.
		 */
		
	}

	public Optional<Member> selectByName(String name) {
		return memberRepository.findByName(name);
		
	}

	public List<Member> selectByNameLike(String name) { // 여러값이라 반환형 List<>
		return memberRepository.findByNameLike(name);
		
	}

	public List<Member> selectByNameLikeDesc(String name) {
		return memberRepository.findByNameLikeOrderByNameDesc(name);

	}

	public List<Member> selectByNameLike(String name, Sort sort) {
		return memberRepository.findByNameLike(name,sort);
	}
}
