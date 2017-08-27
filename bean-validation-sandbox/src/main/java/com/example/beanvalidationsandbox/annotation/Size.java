package com.example.beanvalidationsandbox.annotation;


import com.example.beanvalidationsandbox.validation.SizeValidatorForCollection;
import com.example.beanvalidationsandbox.validation.SizeValidatorForInteger;
import com.example.beanvalidationsandbox.validation.SizeValidatorForString;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({FIELD, METHOD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {SizeValidatorForString.class, SizeValidatorForInteger.class, SizeValidatorForCollection.class})
public @interface Size {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    @Target({METHOD, FIELD, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Size[] value();
    }

}
