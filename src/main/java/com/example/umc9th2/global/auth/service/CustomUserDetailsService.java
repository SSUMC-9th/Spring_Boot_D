package com.example.umc9th2.global.auth.service;

import com.example.umc9th2.domain.User.entity.User;
import com.example.umc9th2.domain.User.exception.UserException;
import com.example.umc9th2.domain.User.exception.code.UserErrorCode;
import com.example.umc9th2.domain.User.repository.UserRepository;
import com.example.umc9th2.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th2.global.auth.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//로그인 시 사용자 조회
//로그인 요청이 오면 스프링 시큐리티 자동 호출
//이메일로 사용자 조회
//조회된 사용자 정보를 customdetails로 반환
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        //로그인 시 입력한 이메일로 사용자 조회
        User user = userRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("존재하지 않는 사용자입니다.")
                );
        //user -> userdetails로 변환
        return new CustomUserDetails(user);
    }

}
