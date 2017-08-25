package com.example.beanvalidationsandbox.validation;

import com.example.beanvalidationsandbox.annotation.Size;
import com.example.beanvalidationsandbox.domain.Domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SizeValidatorForInteger implements ConstraintValidator<Size, Domain<Integer>>{

    int min, max;

    @Override
    public void initialize(Size constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Domain<Integer> value, ConstraintValidatorContext context) {

        System.out.println("SizeValidatorForIntegerが呼ばれました");

        if (value == null) {
            return false;
        }

        if(value.getValue() < min || value.getValue() > max){
            return false;
        }

        return true;
    }
}
