package com.keremsalur.blogapp.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = NotEmptyIfPresentValidator.class)
@Target({ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyIfPresent {
    String message() default "{com.keremsalur.blogapp.validation.NotEmptyIfPresent.message}";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
