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

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("존재하지 않는 사용자입니다.")
                );

        return new CustomUserDetails(user);
    }

}
