package com.study.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 람다 형태 : request -> request (security 7.0부터 람다식만 가능함
		// 예전 형태 http.csrf().disabled()
		http.csrf((csrf)->csrf.disable())
			.cors((cors)->cors.disable())
			.authorizeHttpRequests(request->request
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				//permitALL()은 로그인 안한 사용자도 허용
				.requestMatchers("/").permitAll()
				.requestMatchers("/CSS/**","/js/**","/img/**").permitAll()
				.requestMatchers("/guest/**").permitAll() // 모두에게 권한
				.requestMatchers("/member/**").hasAnyRole("USER","ADMIN") // 한명 이상에게 권한
				.requestMatchers("/admin/**").hasRole("ADMIN") // 한명에게만 권한
				.anyRequest().authenticated()
			);
		/*
		// 스프링부트에서 제공해주는 login
		http.formLogin((formLogin)->
						formLogin.permitAll());
		*/
		
		// 내가 만든 login 폼 사용하기
		http.formLogin((formLogin)-> formLogin
					 .loginPage("/loginForm") // 로그인 폼에 url넣기(default값 : /login)
					 .loginProcessingUrl("/login_check") // action에 넣은 url
					 /*
					  mapping(/login_check)으로 가면 /loginForm이 return이 되는느낌
					  */
					 
					// form에서 지정한 parameter값 가져옴. name값 - username, password
					 .usernameParameter("username") // 기본값(j_username)
					 .passwordParameter("password") // 기본값(j_password)
					 .permitAll());
		http.logout((logout)->
					 logout.permitAll());
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService users() {
		UserDetails user = User.builder()
							   .username("user")
							   .password(pEncoder().encode("1234"))
							   .roles("USER")
							   .build();
		UserDetails admin = User.builder()
								.username("admin")
								.password(pEncoder().encode("1234"))
								.roles("USER","ADMIN")
								.build();
		
		// 메모리에 사용자 정보를 담음 - 아직 DB엔 가지않음
		return new InMemoryUserDetailsManager(user,admin);
		// InMemoryUserDetailsManager는 import해줘야함
	}
	
	// password값이 encoding 된 상태로 넘어가야해서 Encoder만들어줌
	public PasswordEncoder pEncoder() { // import해서 사용
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
	}

}
