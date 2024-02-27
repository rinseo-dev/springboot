package com.study.springjwt.dto;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class EnrollUser {
    private String username;
    private String password;
}

