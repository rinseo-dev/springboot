package com.study.springjwt.jwt;

import com.study.springjwt.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter { // 로그인때 이 필터를 거쳐가도록
    // username, password로 검증 - Spring에서 사용할 수 있는 내장 필터 사용가능하게 필드이름 지정했던것

    // 검증 담당 - AuthenticationManager를 통해 토큰 검증 진행
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil; // 깃허브

    
    // 생성자 방식
    public LoginFilter(AuthenticationManager authenticationManager,JwtUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        //JwtUtil에서 토큰을 검증하고 만드는 작업을 할 것
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        /*
         public Authentication authentication() 이렇게 했을 때 Override가 안됐음

         attemptAuthentication 메서드로 이름을 변경한 이유는
         Spring Security에서 제공하는 UsernamePasswordAuthenticationFilter 클래스의 메서드명이
         attemptAuthentication이기 때문에 다른 이름으로 가져와서 안됨
         
         이미 부모클래스에서 Exception을 해주고 있어서 안해도 되지만 해주는 습관을 들이면 좋음
         */


        //클라이언트 요청에서 username, password 추출
        String username = obtainUsername(request); // request에서 사용자 이름을 가져오는 메소드
        String password = obtainPassword(request); // request에서 비밀번호를 가져오는 메소드

        // 두개를 추출해서 토큰 검증이 필요함
        // 스프링 시큐리티에서 username, password를 검증하기 위해 token에 담아야 함
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, null);
        // 토큰에 담아서 username, password, null과 비교할것. null에는 나중에 role값을 넣을것

        // token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authenticationToken);
        // authenticationManager에 authenticationToken를 전달해줌
    }


    // 로그인이 성공하면 토큰 발행, 실패하면 401

    // 로그인 성공시 실행하는 메소드(실제 jwt를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication){
        CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();
        // 내가 만든 타입 (CustomUserDetails)으로 변환해서 사용
        
        // 내가 사용하고 싶은 걸 가져와서 사용
        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends  GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        // role, username 뽑아온 상태. 이제 토큰 생성 가능
        String token = jwtUtil.createJwt(username, role,1000*60L);
        // username과 역할, 유지되는 시간을 지정하는데 시간이 Long타입임

        response.addHeader("Authorization","Bearer "+token);
        // Authorization가 키가 됨.
        // Authorization:Bearer 토큰 형태로 작성
    }

    // 로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            AuthenticationException failed){
        response.setStatus(401);
    }

}
