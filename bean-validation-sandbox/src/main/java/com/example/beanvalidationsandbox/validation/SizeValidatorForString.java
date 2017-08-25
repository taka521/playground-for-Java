package com.example.beanvalidationsandbox.validation;

import com.example.beanvalidationsandbox.annotation.Size;
import com.example.beanvalidationsandbox.domain.Domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SizeValidatorForString implements ConstraintValidator<Size, Domain<String>> {

    private int min, max;

    @Override
    public void initialize(Size constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Domain<String> value, ConstraintValidatorContext context) {

        System.out.println("SizeValidatorForStringが呼ばれました");

        if (value == null) {
            return false;
        }

        if (value.getValue().length() < min || value.getValue().length() > max){
            return false;
        }

        return true;
    }
}
