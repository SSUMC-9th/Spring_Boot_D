package com.example.umc9th2.domain.User.service.command;

import com.example.umc9th2.domain.User.converter.UserConverter;
import com.example.umc9th2.domain.User.dto.UserReqDTO;
import com.example.umc9th2.domain.User.dto.UserResDTO;
import com.example.umc9th2.domain.User.entity.User;
import com.example.umc9th2.domain.User.entity.mapping.UserFood;
import com.example.umc9th2.global.auth.enums.Role;
import com.example.umc9th2.domain.Food.entity.Food;
import com.example.umc9th2.domain.Food.exception.FoodException;
import com.example.umc9th2.domain.Food.exception.code.FoodErrorCode;
import com.example.umc9th2.domain.User.repository.UserRepository;
import com.example.umc9th2.domain.User.repository.UserFoodRepository;
import com.example.umc9th2.domain.Food.repository.FoodRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final UserFoodRepository userFoodRepository;
    private final FoodRepository foodRepository;

    // Password Encoder 추가
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    @Transactional
    public UserResDTO.JoinDTO signUp(UserReqDTO.JoinDTO dto) {

        // 1. 비밀번호 암호화 (Salted Password)
        String encodedPassword = passwordEncoder.encode(dto.password());

        // 2. 사용자 생성 (기본 권한: ROLE_USER)
        User user = UserConverter.toUser(dto, encodedPassword, Role.ROLE_USER);
        userRepository.save(user);

        // 3. 선호 음식 카테고리 매핑
        if (dto.preferCategory() != null && !dto.preferCategory().isEmpty()) {

            List<UserFood> userFoodList = dto.preferCategory().stream()
                    .map(id -> UserFood.builder()
                            .user(user)
                            .food(
                                    foodRepository.findById(id)
                                            .orElseThrow(() ->
                                                    new FoodException(FoodErrorCode.NOT_FOUND)
                                            )
                            )
                            .build())
                    .toList();

            userFoodRepository.saveAll(userFoodList);
        }

        // 4. 응답 DTO 반환
        return UserConverter.toJoinDTO(user);
    }
}
