package com.umc9th.peter.global.annotation;

import com.umc9th.peter.global.validator.PageNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PageNumber {

    String message() default "페이지 번호는 1부터 시작합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
