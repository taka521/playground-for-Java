package com.example.beanvalidationsandbox;

import org.hibernate.validator.constraints.Range;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Size;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultValidationTest {

    private class TestBean {

        @Range(min = 3, max = 15)
        public Integer integer;

        @Size(min = 5, max = 10)
        public String string;

    }

    @Autowired
    Validator validator;

    @Test
    public void test_defaultValidation(){

        TestBean bean = new TestBean();
        bean.integer = Integer.valueOf(20);
        bean.string = "aa";

        Set<ConstraintViolation<TestBean>> result = validator.validate(bean);
        result.forEach(System.out::println);
    }


}
