package com.study.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.domain.Member;

@Repository // Repository에도 annotation달아줌
public interface MemberRepository extends JpaRepository<Member, Long> {

	// 기본적으로 사용할 수 있는 메서드들을 제외하고 만들어서 사용한걸 repository에 작성함
	
	// find + 필드명으로 찾기 가능
	Optional<Member> findByEmail(String email);

	Optional<Member> findByName(String name);

	List<Member> findByNameLike(String name);//여러명이라 리스트

	List<Member> findByNameLikeOrderByNameDesc(String name);
	//List<Member> findByNameLikeOrderByNameAscEmailDesc(String name);
	// 이름은 내림차순, 이메일은 오름차순으로 정렬 할 수 있음.
	// order by는 [필드이름 오름차순|내림차순]

	List<Member> findByNameLike(String name, Sort sort);

}
