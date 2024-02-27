package com.study.dto;

import com.study.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor // 모든 필드를 가진 생성자
@NoArgsConstructor // 비어있는 생성자 - jpa에서 사용
public class UserDto {
	private String id;
	private String name;
	
	/*
	모든 필드를 가진 생성자는 이런 형태임.
	public UserDto(String id, String name) {
		this.id = id;
		this.name = name;
	}
	*/
	
	// @AllArgsConstructors는 필드를 사용했고 여기서는 Member라는 객체를 가져와서 사용
	// 이렇게 되면 @AllArgsConstructors를 설정하지 않아도 2개짜리 생성자로 인식함
	public UserDto(Member m) {
		id = m.getId();
		name = m.getName();
	}
}
