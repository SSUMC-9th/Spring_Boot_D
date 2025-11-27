package com.umc9th.peter.global.annotation;

import com.umc9th.peter.global.validator.FoodCategoryExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FoodCategoryExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistFoodCategories {

    String message() default "존재하지 않는 범주입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
