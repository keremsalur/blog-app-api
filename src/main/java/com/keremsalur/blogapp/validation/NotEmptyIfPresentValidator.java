package com.keremsalur.blogapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyIfPresentValidator implements ConstraintValidator<NotEmptyIfPresent, Object> {
    @Override
    public void initialize(NotEmptyIfPresent constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if(object == null){
            return true;
        }
        else if(object.getClass() == String.class){
            String value = (String) object;
            if(value == null || !value.isBlank()){
                return true;
            }
            return false;
        }
        else if(object.getClass() == byte[].class){
            byte[] value = (byte[]) object;
            if(value == null || value.length != 0){
                return true;
            }
            return false;
        }
        return false;
    }
}
