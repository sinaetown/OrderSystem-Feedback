package com.encore.ordering.securities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JwtTokenProvider {
    public String createToken(String email, String role) {
//        claims : 토큰 사용자에 대한 속성이나 데이터 포함, 주로 페이로드를 의미
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);
        Date now = new Date();
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setClaims(claims);
        jwtBuilder.setIssuedAt(now);
        jwtBuilder.setExpiration(new Date(now.getTime() + 30 * 60 * 1000L));
        jwtBuilder.signWith(SignatureAlgorithm.HS256, "mysecret");
        return jwtBuilder.compact();
    }
}
