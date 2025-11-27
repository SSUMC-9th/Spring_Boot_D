package com.umc9th.peter.global.validator;

import com.umc9th.peter.global.annotation.PageNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageNumberValidator implements ConstraintValidator<PageNumber, Integer> {

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        return page > 0;
    }

}
