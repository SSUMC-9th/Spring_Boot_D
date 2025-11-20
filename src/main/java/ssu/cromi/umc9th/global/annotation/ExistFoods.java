package ssu.cromi.umc9th.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ssu.cromi.umc9th.global.validator.FoodExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FoodExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistFoods {
    String message() default "해당음식이 존재하지 않습니다,";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
