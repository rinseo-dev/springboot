package com.study.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @AllArgsConstructor를 넣으면 필드가 없는 기본생성자가 사라짐
 * JPA에서는 필드가 없는 기본생성자를 사용하기 때문에 무조건 있어야 함
 * 그래서 @AllArgsConstructor를 적용했을 때는
 * @NoArgsConstructor도 적용해줘야 필드없는 생성자를 사용할 수 있음
 * - outline에서 생성자에 대한 확인 가능
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="MEMBERJPA01") // @Table이 아니라 @Entity에 써도 테이블명 지정 가능
public class Member {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	@Column(name="create_date")
	private LocalDate createDate;
}