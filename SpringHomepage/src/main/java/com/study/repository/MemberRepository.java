package com.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
	//@Repository어노테이션 달아주고 JpaRepository 상속받기 <Entity이름, @Id필드이름>

}
