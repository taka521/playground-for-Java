package com.example.beanvalidationsandbox.validation;

import com.example.beanvalidationsandbox.annotation.Size;
import com.example.beanvalidationsandbox.domain.Domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class SizeValidatorForCollection implements ConstraintValidator<Size, Domain<Collection<?>>> {

    int min, max;

    @Override
    public void initialize(Size constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Domain<Collection<?>> value, ConstraintValidatorContext context) {

        System.out.println("SizeValidatorForCollectionが呼ばれました");

        if (value == null) {
            return false;
        }

        int size = value.getValue().size();
        if (size > min || size > max) {
            return false;
        }

        return true;
    }
}
