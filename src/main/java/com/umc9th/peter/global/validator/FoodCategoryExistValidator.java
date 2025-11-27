package com.umc9th.peter.global.validator;

import com.umc9th.peter.domain.member.exception.code.FoodErrorCode;
import com.umc9th.peter.domain.member.repository.FoodCategoryRepository;
import com.umc9th.peter.global.annotation.ExistFoodCategories;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodCategoryExistValidator implements ConstraintValidator<ExistFoodCategories, List<Long>> {

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public boolean isValid(List<Long> ids, ConstraintValidatorContext context) {
        boolean valid = ids.stream()
                .allMatch(foodCategoryRepository::existsById);

        if (!valid) {
            // default message를 다음 error code의 message로 덮어씌움
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(FoodErrorCode.NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return false;
    }

}
