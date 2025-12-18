package com.example.umc9th2.global.auth.jwt;

import com.example.umc9th2.global.auth.principal.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;
//로그인 성공시 jwt 발급이후 요청에서 토큰 검증
@Component
public class JwtUtil {
    //JWT 서명에 사용할 비밀키
    private final SecretKey secretKey;
    //access token 만료 시간
    private final Duration accessExpiration;

    public JwtUtil(//application.yml에서 값 주입
            @Value("${jwt.token.secretKey}") String secret,
            @Value("${jwt.token.expiration.access}") Long accessExpiration
    ) {//문자열 secretkey ->HMAC-SHA 키로 변환
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpiration);//밀리초->Duration
    }

    // AccessToken 생성
    public String createAccessToken(CustomUserDetails user) {
        return createToken(user, accessExpiration);
    }

    /** 토큰에서 이메일 가져오기
     *
     * @param token 유저 정보를 추출할 토큰
     * @return 유저 이메일을 토큰에서 추출합니다
     */
    //jwt에서 이메일 추출
    public String getEmail(String token) {
        try {
            //jwt 파싱 후 subject 추출
            return getClaims(token).getPayload().getSubject(); // Parsing해서 Subject 가져오기
        } catch (JwtException e) {
            return null;
        }
    }

    /** 토큰 유효성 확인
     *
     * @param token 유효한지 확인할 토큰
     * @return True, False 반환합니다
     */
    public boolean isValid(String token) {
        try {
            //파싱 성공 = 유효한 토큰
            getClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // 토큰 생성
    private String createToken(CustomUserDetails user, Duration expiration) {
        Instant now = Instant.now();

        // 인가 정보 - 권한 정보를 문자열로 반환
        String authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(user.getUsername()) // User 이메일을 Subject로
                .claim("role", authorities)//권한 정보
                .claim("email", user.getUsername())
                .issuedAt(Date.from(now)) // 발급 시간 - 언제 발급한지
                .expiration(Date.from(now.plus(expiration))) // 만료 시간 - 언제까지 유효한지
                .signWith(secretKey) // 서명 키 - sign할 Key
                .compact();
    }

    // jwt 파싱 - 토큰 정보 가져오기
    private Jws<Claims> getClaims(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(60)
                .build()
                .parseSignedClaims(token);
    }
}
