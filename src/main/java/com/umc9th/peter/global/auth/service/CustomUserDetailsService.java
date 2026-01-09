package com.umc9th.peter.global.auth.service;

import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.member.exception.MemberException;
import com.umc9th.peter.domain.member.exception.code.MemberErrorCode;
import com.umc9th.peter.domain.member.repository.MemberRepository;
import com.umc9th.peter.global.auth.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        return new CustomUserDetails(member);
    }
}
