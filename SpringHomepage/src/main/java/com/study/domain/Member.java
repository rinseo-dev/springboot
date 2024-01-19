package com.study.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Member {
	@Id
	private String id;
	/*
	 * 필드에  not null 적용하는 방법2가지
	 * @NonNull : lombok으로 import
	 * 			  sql쿼리를 보내기 전에 Exception이 발생
	 * 			  NonNull을 붙인 필드를 포함하는 생성자가 발생하고 비어있는 생성자 사라짐
	 * 			  그래서 사용할 때 @NoArgsConstructor로 아무것도 없는 생성자 만들어서 사용
	 * @Column(nullable=false) : 생성자가 생기는 방법 / DB로 넘어가고나서 예외발생
	 */
	@NonNull
	private String password;
	@NonNull 
	private String name;
	private String email;
	private LocalDate birthday;
	private String gender;
	private String phone;
	private String address;
	
	// 생성할 때 시간 등록
	@CreatedDate
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	// 수정할 때 시간 등록
	@LastModifiedDate
	@Column(name="update_date")
	private LocalDateTime updatteDate;
}
