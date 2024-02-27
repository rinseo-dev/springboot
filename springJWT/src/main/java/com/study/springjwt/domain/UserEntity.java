package com.study.springjwt.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class UserEntity {
    @Id
    @SequenceGenerator(
            name="myUserSEQ",
            sequenceName = "User_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(generator="myUserSEQ") // 깃허브
    private Long id;

    private String username;
    private String password;
    private String role;

}
