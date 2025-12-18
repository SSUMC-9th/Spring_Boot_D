package com.example.umc9th2.global.auth.principal;

import com.example.umc9th2.domain.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
//spring security가 user 엔티티를 인식하도록 변환
//jwt 검증 시 이메일로 사용자 조회, 인증 객체 생성에 사용
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    //실제 user 엔티티
    private final User user;
    //사용자 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> user.getRole().name());
    }
    //암호화된 비밀번호
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    //로그인 ID(email)
    @Override
    public String getUsername() {
        return user.getEmail();
    }
    //계정 상태 관련
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}

//user 엔티티를 userdetails로 변환
//@RequiredArgsConstructor
//public class CustomUserDetails implements UserDetails {

// 실제 DB에 저장된 Member 엔티티
//    private final Member member;
//
//    //사용자 권한 반환
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // ROLE_USER, ROLE_ADMIN 등
//        return List.of(() -> member.getRole().toString());
//    }
//
//    // 암호화된 비밀번호 반환
//    @Override
//    public String getPassword() {
//        return member.getPassword();
//    }
//
//    // 로그인 ID (email)
//    @Override
//    public String getUsername() {
//        return member.getEmail();
//    }
//
//    // 계정 상태
//    @Override public boolean isAccountNonExpired() { return true; }
//    @Override public boolean isAccountNonLocked() { return true; }
//    @Override public boolean isCredentialsNonExpired() { return true; }
//    @Override public boolean isEnabled() { return true; }
//}
