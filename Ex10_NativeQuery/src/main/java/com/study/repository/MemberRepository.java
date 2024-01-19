package com.study.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.domain.Member;

@Repository // Repository에도 annotation달아줌
public interface MemberRepository extends JpaRepository<Member, Long> {
	// JPQL쿼리 : from뒤에는 영속성에 있는 Entity명(DB테이블이 아님. 반드시 대문자로 작성)
	// select *을 하는 대신 테이블이름에 별칭을 주고 select 뒤에 별칭을 넣어줌
	@Query("select m from JPAPAGING m where m.name like :name1 order by m.id desc")
	// 쌤피셜 : :name1은 @Param으로 넘어온 이름과 동일하게 지정.
	
	List<Member> findMembers(@Param("name1") String name2);
	// @Param에 있는 name1값은 Service에서 넘어오는 값이 맞지만
	// @Query를 사용해서 지정..?
	// like로 넘어온 :name1과 @Param("name1")이 같음.

	@Query("select m from JPAPAGING m where m.name like :name1")
	List<Member> findMembers(@Param("name1") String name, Sort sort);

	@Query("select m from JPAPAGING m where m.name like :name1") 
	Page<Member> findMembers(@Param("name1") String name, Pageable pageable);

	
	// 일반 SQL 쿼리
	@Query(value="select * from jpapaging where name like :name1 order by id desc",
		   nativeQuery=true)//nativeQuery=true일반쿼리를 사용하겠다는것. 기본값 falsse
	List<Member> findMembersNative(@Param("name1") String name); // Sort sort제외했을경우 sql에 넣어줌 order by
	
	
}
