package com.study.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name="BOARDAM")
public class Board {
	@Id
	@GeneratedValue
	private Long bno;
	private String title;
	private String content;
	
	@ManyToOne // 다대일 관계
	@JoinColumn(name="writer") // name에는 어디에 걸어줄건지를 적으면 됨
	private Member member; // Member객체의 @Id 필드의 값이 들어감
	/*
	 * board에는 writer가 있고, 테이블에 넘기면 field명은 writer
	 * 실제 DB에는  | bno | title | content | writer| 가 존재하고
	 * wirter에 들어가는 이름이기 때문에 member객체를 가지고 넣어줘야함
	 * member객체의 id가 writer에 들어가게됨 
	 * 그럼 DB에 객체없이 writer에 user01이라는 id가 들어가게됨.
	 * 값을 받아올때도 member객체로 받아와야함
	 */
	
	
}
