package com.study.springjwt.jwt;

import com.study.springjwt.domain.UserEntity;
import com.study.springjwt.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {// 1회 실행되는 필터를 extends
    // OncePerRequestFilter에서 메소드 가져옴

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil; //jwtUtil을 주입
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        
        
        // request에서 Authorization헤더를 찾음- LoginFilter에서 사용한 Authorization가져옴
        String authorization = request.getHeader("Authorization");
        
        // Authorization검증
        if(authorization == null || !authorization.startsWith("Bearer ")){
            // authorization이 없거나, 'Bearer '로 시작되거나 <- 공백도 필요함 뒤에 token이 붙을것이므로
            System.out.println("token null");
            filterChain.doFilter(request,response);
            return; // 내가 받은걸 그대로 다시 리턴(?)
        }

        // Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];
                //index[0]에는 Bearer, index[1]에는 token값이 들어있게 됨
        System.out.println("token : " + token);

        // 토큰 소멸시간 검증 - 토큰 발행 후에는 시간만 검증하면 됨. 이미 DB에서 username 등을 가져왔으므로
        if(jwtUtil.isExpired(token)){
            System.out.println("token expired");
            filterChain.doFilter(request,response);
            return;
        }
        
        // 토큰에서 username과 role획득
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword("jwtpassword1234"); // 아무값이나 넣음
        userEntity.setRole(role);

        // userEntity를 session에 일시적으로 넣으면됨
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
        Authentication authToken =
                new UsernamePasswordAuthenticationToken(customUserDetails,null,
                        customUserDetails.getAuthorities());

        // user 세션 만들기
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request,response);
    } 
}
