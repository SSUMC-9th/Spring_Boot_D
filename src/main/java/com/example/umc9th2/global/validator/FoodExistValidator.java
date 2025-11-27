package com.example.umc9th2.global.validator;

import com.example.umc9th2.domain.Food.repository.FoodRepository;
import com.example.umc9th2.global.annotation.ExistFoods;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodExistValidator implements ConstraintValidator<ExistFoods, List<Long>> {

    private final FoodRepository foodRepository;

    @Override
    public boolean isValid(List<Long> value, ConstraintValidatorContext context) {

        // null 이거나 empty면 통과
        if (value == null || value.isEmpty()) return true;

        for (Long id : value) {
            if (!foodRepository.existsById(id)) {

                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("해당 음식을 찾지 못했습니다.")
                        .addPropertyNode("preferCategory")
                        .addConstraintViolation();

                return false;
            }
        }
        return true;
    }
}
