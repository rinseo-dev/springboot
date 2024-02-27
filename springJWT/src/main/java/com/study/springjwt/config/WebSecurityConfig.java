package com.study.springjwt.config;

import com.study.springjwt.jwt.JwtFilter;
import com.study.springjwt.jwt.JwtUtil;
import com.study.springjwt.jwt.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {
    // AuthenticationManager가 인자로 받을 때 AuthenicationConfiguration 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;

    public WebSecurityConfig(AuthenticationConfiguration authenticationConfiguration, JwtUtil jwtUtil){
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    // AuthenticationManager Bean에 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        // authenticationManager를 실행하면 AuthenticationConfiguration를 바로 리턴하게끔
        return configuration.getAuthenticationManager();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 사이트간 요청 위조 : 사용안함
        http.csrf((auth)->auth.disable());

        // 로그인폼 토큰을 사용할 것이기 때문에 사용안함
        http.formLogin((auth)->auth.disable());

        // http basic 인증방식 : 사용안함
        http.httpBasic((auth)->auth.disable());

        // 경로별 인가 작업
        http.authorizeHttpRequests((auth)->auth
                .requestMatchers("/login","/","/enroll").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()); // 나머지 모든 것은 인증 필요
        
        // JwtFilter 추가
        http
                .addFilterBefore(new JwtFilter(jwtUtil),LoginFilter.class);
        
        
        // LoginFilter추가
        /*
         * .addFilterAt -> 원하는 자리에 등록
         * .addFilterBefore -> 해당하는 필터 전에 등록
         * .addFilterAfter -> 해당하는 필터 이후에 등록
         */
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), 
                        UsernamePasswordAuthenticationFilter.class);
                       // jwtUtil을 추가
                // LoginFilter를 보면 생성자에 매니저를 매개변수로 받아야함

       http.sessionManagement((session)->session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

        // session 설정
        /*
         * - stateless 상태 : 서버에서 HTTP와 같은 client의 이전 상태를 기록하지 않고 접속
         *   REST 개념에서 각각의 요청은 독립적인 stateless방식이고,
         *   client가 상태정보를 모두 관리할 책임이 있다.
         *
         * - stateful 상태 : 서버에서 client의 이전 상태를 기록
         */

        // 이렇게 합쳐서 작성해도 됨
        /*
        http.csrf((auth)->auth.disable())
            .formLogin((auth)->auth.disable())
            .httpBasic((auth)->auth.disable())
                .authorizeHttpRequests((auth)->auth
                .requestMatchers("/login","/","/enroll").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement((session)->session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
        return http.build();*/
    }
}
