package com.example.umc9th2.domain.User.service.query;

import com.example.umc9th2.domain.User.dto.UserReqDTO;
import com.example.umc9th2.domain.User.dto.UserResDTO;
import com.example.umc9th2.domain.User.entity.User;
import com.example.umc9th2.domain.User.exception.UserException;
import com.example.umc9th2.domain.User.exception.code.UserErrorCode;
import com.example.umc9th2.domain.User.repository.UserRepository;
import com.example.umc9th2.global.auth.jwt.JwtUtil;
import com.example.umc9th2.global.auth.principal.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.umc9th2.domain.User.converter.UserConverter;
//로그인 성공시 jwt 발급, 클라이언트는 이후 요청에 토큰 사용
@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public UserResDTO.LoginDTO login(UserReqDTO.LoginDTO dto) {
        //이메일로 사용자 조회
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));
        //비밀번호 검증
        if (!encoder.matches(dto.password(), user.getPassword())) {
            throw new UserException(UserErrorCode.INVALID_PASSWORD);
        }
        //jwt 발급용 userdetails 생성
        CustomUserDetails userDetails = new CustomUserDetails(user);
        //accress token 생성
        String accessToken = jwtUtil.createAccessToken(userDetails);
        //토큰을 포함한 응답 반환
        return UserConverter.LoginDTO(user, accessToken);
    }
}
