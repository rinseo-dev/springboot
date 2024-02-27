package com.study.springjwt.jwt;


import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class JwtUtil {
    private SecretKey secretKey;

    // application.properties에 정의한 SecretKey를 불러온다
    public JwtUtil(@Value("${spring.jwt.secret}") String secret){
        // Value import는 lombok이 아니라 springframework
        //spring.jwt에 존재하는걸 가져옴
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    // 검증
    public String getUsername(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)
                .getPayload().get("username",String.class);
        // 검증에는 Jwts를 사용함 / verifyWith는 키가 맞는건지 확인함 / token안에 있는 username을 Payload로 가져와서 검증
    }

    public String getRole(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)
                .getPayload().get("role",String.class);

        // 검증에는 Jwts를 사용함 / verifyWith는 키가 맞는건지 확인함 / token안에 있는 username을 Payload로 가져와서 검증
    }

    // 유효한 토큰인지 검증
    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)
                .getPayload().getExpiration().before(new Date());
    }

    // 토큰 생성 - 로그인필터에서 createJwt를 활용할것
    public String createJwt(String username, String role, Long expiredMs){
        return Jwts.builder()
                .claim("username",username)
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis())) // 현재 발생 시간
                .expiration(new Date(System.currentTimeMillis()+ expiredMs)) // 소멸시간
                .signWith(secretKey) // 시그니쳐를 넣어주는데 아까 만든 secretKey넣어줌
                .compact();
    }

}
