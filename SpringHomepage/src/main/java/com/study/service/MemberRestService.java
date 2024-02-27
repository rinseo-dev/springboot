package com.study.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.domain.Member;
import com.study.dto.UserDto;
import com.study.repository.MemberRepository;

@Service
public class MemberRestService {

	@Autowired
	MemberRepository memberRepository;

	public Member saveUserDto(UserDto userDto) {
		// userDto를 Member로 바꿔줘야함.
		Member member = new Member();
		member.setId(userDto.getId());
		member.setPassword("1234"); // 임의로 넣어줌
		member.setName(userDto.getName());
		
		return memberRepository.save(member);
		// 결과가 member로 반환됨
	}

	public UserDto getUserById(String id) {
		/*
		// Optional<Member> m = memberRepository.findById(id);
		// findById()를 써서 optional로 가져오게 됐고, .get()을 사용해서 벗겨서 가져옴
		
		Member m = memberRepository.findById(id).get(); // .get()으로 값 하나 가져옴
		UserDto userDto = new UserDto();
		userDto.setId(m.getId());
		userDto.setName(m.getName());
		
		return userDto; 이렇게도 되지만 아래 한줄로도 가능함
		 */
		return new UserDto(memberRepository.findById(id).get());
	}

	public List<Member> getUserAll() {
		return memberRepository.findAll();
		
	}

	public List<UserDto> getUserDtoAll() {
		//List<Member> mList = memberRepository.findAll();
		List<UserDto> uList = new ArrayList<>();
		
		//하나씩 꺼내서 Member의 자료형에 넣어줌
		//for(Member m : mList) { mList없이 아래처럼 바로 넣어줘도 됨
		for(Member m : memberRepository.findAll()) {
			uList.add(new UserDto(m)); // UserDto(Member m)생성자를 사용한것
		}
		return uList;
	}
	
}
