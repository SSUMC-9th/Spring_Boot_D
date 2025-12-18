package com.example.UMC9th.domain.user.service.query;

import com.example.UMC9th.domain.user.converter.UserConverter;
import com.example.UMC9th.domain.user.dto.UserReqDTO;
import com.example.UMC9th.domain.user.dto.UserResDTO;
import com.example.UMC9th.domain.user.entity.User;
import com.example.UMC9th.domain.user.exception.UserException;
import com.example.UMC9th.domain.user.exception.code.UserErrorCode;
import com.example.UMC9th.domain.user.repository.UserRepository;
import com.example.UMC9th.domain.user.service.Detail.CustomUserDetails;
import com.example.UMC9th.global.auth.entity.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService{

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public UserResDTO.LoginDTO login(
            UserReqDTO.@Valid LoginDTO dto
    ) {

        // Member 조회
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), user.getPassword())){
            throw new UserException(UserErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(user);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return UserConverter.toLoginDTO(user, accessToken);
    }
}