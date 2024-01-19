package com.study.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.domain.Member;
import com.study.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;

	public boolean idCheck(String id) { // 리턴값 boolean으로 바로 넘김
		// 아이디가 DB에 존재하는지 확인하는 jpa메소드 existsById()사용 - 반환형 T/F임
		return memberRepository.existsById(id);
	}

	public Member insert(Member member) { //반환형 Member
		return memberRepository.save(member);
		
	}

	public Member login(Member member) {
		Optional<Member> loginUser = memberRepository.findById(member.getId());
		//findById는 기존 JpaRepository에 있어서 생성하지 않고 사용 가능
		// 반환형은 Optional이므로 적용해줘야하고, 비어있는지 아닌지를 확인하는 방법으로 사용
				
		// 체크하는 if문은 controller로 가져가서 해도 됨
		if(loginUser.isPresent()) { // 비어있으면 true, isPresent()는 존재하면 true를 반환
			return loginUser.get();
			// .get()으로 wrap을 하나 벗여서 return하기 때문에 반환형도 void가 아니라 Member반환
		}else {
			return null;
		}
	}
}
