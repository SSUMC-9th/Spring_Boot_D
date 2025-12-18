package com.example.UMC9th.domain.user.service.Detail;

import com.example.UMC9th.domain.user.entity.User;
import com.example.UMC9th.domain.user.exception.UserException;
import com.example.UMC9th.domain.user.exception.code.UserErrorCode;
import com.example.UMC9th.domain.user.repository.UserRepository;
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
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        // 검증할 Member 조회
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));
        // CustomUserDetails 반환
        return new CustomUserDetails(user);
    }
}