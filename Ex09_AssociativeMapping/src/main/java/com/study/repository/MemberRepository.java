package com.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.domain.Member;

@Repository // Repository에도 annotation달아줌
public interface MemberRepository extends JpaRepository<Member, Long> {

	Page<Member> findByNameLike(String name, Pageable pageable);
}
