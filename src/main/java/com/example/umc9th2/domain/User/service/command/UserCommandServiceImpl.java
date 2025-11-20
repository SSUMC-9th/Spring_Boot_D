package com.example.umc9th2.domain.User.service.command;

import com.example.umc9th2.domain.User.converter.UserConverter;
import com.example.umc9th2.domain.User.dto.UserReqDTO;
import com.example.umc9th2.domain.User.dto.UserResDTO;
import com.example.umc9th2.domain.User.entity.User;
import com.example.umc9th2.domain.User.entity.mapping.UserFood;
import com.example.umc9th2.domain.Food.entity.Food;
import com.example.umc9th2.domain.Food.exception.FoodException;
import com.example.umc9th2.domain.Food.exception.code.FoodErrorCode;

import com.example.umc9th2.domain.User.repository.UserRepository;
import com.example.umc9th2.domain.User.repository.UserFoodRepository;
import com.example.umc9th2.domain.Food.repository.FoodRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final UserFoodRepository userFoodRepository;

    // FoodRepository는 Food 도메인에 있어야만 정상 import 됨!!
    private final FoodRepository foodRepository;

    @Override
    @Transactional
    public UserResDTO.JoinDTO signUp(UserReqDTO.JoinDTO dto) {

        User user = UserConverter.toUser(dto);
        userRepository.save(user);

        if (dto.preferCategory() != null && !dto.preferCategory().isEmpty()) {

            List<UserFood> userFoodList = dto.preferCategory().stream()
                    .map(id -> UserFood.builder()
                            .user(user)
                            .food(
                                    foodRepository.findById(id)
                                            .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND))
                            )
                            .build())
                    .toList();

            userFoodRepository.saveAll(userFoodList);
        }

        return UserConverter.toJoinDTO(user);
    }
}
