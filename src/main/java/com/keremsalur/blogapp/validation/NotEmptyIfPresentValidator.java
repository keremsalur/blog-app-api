package com.keremsalur.blogapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyIfPresentValidator implements ConstraintValidator<NotEmptyIfPresent, String> {
    @Override
    public void initialize(NotEmptyIfPresent constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || !value.isBlank()){
            return true;
        }
        return false;
    }
}
