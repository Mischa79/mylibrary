package com.example.mylibrary;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// будет использоваться для проверки целого
// на вхождение в диапазон
@Target({ElementType.FIELD}) // целью нашей аннотации будет поле класса
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BetweenValidator.class)
@Documented
public @interface Between {
    String message() default "{Between.invalid}";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};

    int from(); // параметр "от"
    int to(); // параметр "до"
}
