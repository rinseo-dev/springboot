package com.study.springjwt.repository;

import com.study.springjwt.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    // void였던 반환값을 UserEntity로 변경해줌
}
