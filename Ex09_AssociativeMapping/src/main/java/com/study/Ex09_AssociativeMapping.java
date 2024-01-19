package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Ex09_AssociativeMapping {

	public static void main(String[] args) {
		SpringApplication.run(Ex09_AssociativeMapping.class, args);
	}

}
