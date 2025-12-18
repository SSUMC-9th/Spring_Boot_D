package com.example.UMC9th.domain.user.service.command;

import com.example.UMC9th.domain.user.converter.UserConverter;
import com.example.UMC9th.domain.user.dto.UserReqDTO;
import com.example.UMC9th.domain.user.dto.UserResDTO;
import com.example.UMC9th.domain.user.entity.Food;
import com.example.UMC9th.domain.user.entity.User;
import com.example.UMC9th.domain.user.entity.mapping.UserFood;
import com.example.UMC9th.domain.user.exception.FoodException;
import com.example.UMC9th.domain.user.exception.code.FoodErrorCode;
import com.example.UMC9th.domain.user.repository.UserRepository;
import com.example.UMC9th.domain.user.repository.FoodRepository;
import com.example.UMC9th.domain.user.repository.UserFoodRepository;

import com.example.UMC9th.global.auth.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final UserFoodRepository userFoodRepository;
    private final FoodRepository foodRepository;
    private final PassworddEncoder passwordEncoder;

    //회원가입
    @Override
    @Transactional
    public UserResDTO.JoinDTO signup(
            UserReqDTO.JoinDTO dto
    ){
        // 사용자 생성
        String salt = passwordEncoder.encode(dto.password());

        User user = UserConverter.toUser(dto, salt, Role.ROLE_USER);

        // DB 적용
        userRepository.save(user);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory().size() > 1){
            List<UserFood> userFoodList = new ArrayList<>();

            // 선호 음식 ID별 조회
            for (Long id : dto.preferCategory()){

                // 음식 존재 여부 검증
                Food food = foodRepository.findById(id)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

                // UserFood 엔티티 생성 (컨버터 사용해야 함)
                UserFood userFood = UserFood.builder()
                        .user(user)
                        .food(food)
                        .build();

                // 사용자 - 음식 (선호 음식) 추가
                userFoodList.add(userFood);
            }

            // 모든 선호 음식 추가: DB 적용
            userFoodRepository.saveAll(userFoodList);
        }


        // 응답 DTO 생성
        return UserConverter.toJoinDTO(user);
    }
}