package com.example.beanvalidationsandbox;

import com.example.beanvalidationsandbox.annotation.Size;
import com.example.beanvalidationsandbox.domain.CollectionDomain;
import com.example.beanvalidationsandbox.domain.IntDomain;
import com.example.beanvalidationsandbox.domain.StringDomain;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomValidateTest {

    @Size(min = 4, message = "{com.example.beanvalidationsandbox.annotation.Size}")
    StringDomain stringDomain;

    @Size(max = 4, message = "{Size}")
    IntDomain intDomain;

    @Size(max = 4, message = "{validation.MSG002}")
    CollectionDomain collectionDomain;


    @Autowired
    Validator validator;

    @Test
    public void test() {

        CustomValidateTest target = new CustomValidateTest();
        target.stringDomain = new StringDomain().setValue("");
        target.intDomain = new IntDomain().setValue(5);
        target.collectionDomain = new CollectionDomain().setValue(Arrays.asList(1, 2, 3, 4, 5));

        Set<ConstraintViolation<CustomValidateTest>> violations = validator.validate(target);
        Assert.assertThat(violations.size(), is(3));
        violations.forEach(System.out::println);
    }

}
