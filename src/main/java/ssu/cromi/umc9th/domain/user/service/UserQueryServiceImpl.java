package ssu.cromi.umc9th.domain.user.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssu.cromi.umc9th.domain.user.converter.UserConverter;
import ssu.cromi.umc9th.domain.user.dto.UserReqDTO;
import ssu.cromi.umc9th.domain.user.dto.UserResDTO;
import ssu.cromi.umc9th.domain.user.entity.User;
import ssu.cromi.umc9th.domain.user.exception.UserException.UserException;
import ssu.cromi.umc9th.domain.user.exception.code.UserErrorCode;
import ssu.cromi.umc9th.domain.user.repository.UserRepository;
import ssu.cromi.umc9th.global.auth.entity.JwtUtil;
import ssu.cromi.umc9th.global.auth.service.CustomUserDetails;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResDTO.LoginDTO login(UserReqDTO.@Valid LoginDTO dto) {
        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new UserException(UserErrorCode.NOT_FOUND);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(user);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return UserConverter.toLoginDTO(user, accessToken);
    }
}