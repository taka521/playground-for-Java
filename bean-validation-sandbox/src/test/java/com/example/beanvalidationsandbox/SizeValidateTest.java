package com.example.beanvalidationsandbox;

import com.example.beanvalidationsandbox.annotation.Size;
import com.example.beanvalidationsandbox.domain.CollectionDomain;
import com.example.beanvalidationsandbox.domain.IntDomain;
import com.example.beanvalidationsandbox.domain.StringDomain;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.Matchers.is;

public class SizeValidateTest {

    @Size(min = 4, message = "4文字に満たない!!")
    StringDomain stringDomain;

    @Size(max = 4, message = "４より大きい")
    IntDomain intDomain;

    @Size(max = 4, message = "要素数が4より大きい")
    CollectionDomain collectionDomain;


    @Test
    public void test() {

        SizeValidateTest target = new SizeValidateTest();
        target.stringDomain = new StringDomain().setValue("");
        target.intDomain = new IntDomain().setValue(5);
        target.collectionDomain = new CollectionDomain().setValue(Arrays.asList(1, 2, 3, 4, 5));

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<SizeValidateTest>> violations = validator.validate(target);
        Assert.assertThat(violations.size(), is(3));
        violations.forEach(System.out::println);
    }


}
