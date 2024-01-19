package com.study.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="JPAPAGING")
public class Member {
	@Id
	@SequenceGenerator(
			name="mySequence",
			sequenceName="jpaPaging_seq",
			initialValue=1,
			allocationSize=1
	)
	@GeneratedValue(generator="mySequence")
	private Long id;
	
	private String email;
	private String name;
	
	public Member(String email, String name) {
		this.email = email;
		this.name = name;
	}
	
	
}