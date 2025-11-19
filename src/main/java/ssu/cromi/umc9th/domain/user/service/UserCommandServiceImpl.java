package ssu.cromi.umc9th.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssu.cromi.umc9th.domain.food.entity.FoodCategory;
import ssu.cromi.umc9th.domain.food.entity.UserFood;
import ssu.cromi.umc9th.domain.food.exception.FoodException.FoodException;
import ssu.cromi.umc9th.domain.food.exception.code.FoodErrorCode;
import ssu.cromi.umc9th.domain.food.repository.FoodRepository;
import ssu.cromi.umc9th.domain.food.repository.UserFoodRepository;
import ssu.cromi.umc9th.domain.user.converter.UserConverter;
import ssu.cromi.umc9th.domain.user.dto.UserReqDTO;
import ssu.cromi.umc9th.domain.user.dto.UserResDTO;
import ssu.cromi.umc9th.domain.user.entity.User;
import ssu.cromi.umc9th.domain.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService{
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final UserFoodRepository userFoodRepository;

    //회원가입
    @Override
    @Transactional
    public UserResDTO.JoinDTO signup(
            UserReqDTO.JoinDTO dto
    ) {
        //사용자 생성
        User user = UserConverter.toUser(dto);
        // DB적용
        userRepository.save(user);

        //선호 음식 존재 여부 확인
        if(dto.preferCategory().size() > 1){
            List<UserFood> userFood = dto.preferCategory().stream()
                    .map(id -> UserFood.builder()
                            .user(user)
                            .foodCategory(foodRepository.findById(id)
                                    .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    )
                    .collect(Collectors.toList());

        userFoodRepository.saveAll(userFood);
        }

        return UserConverter.toJoinDTO(user);
    }

}
