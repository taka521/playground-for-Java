package com.example.beanvalidationsandbox.bean;


import com.example.beanvalidationsandbox.domain.CollectionDomain;
import com.example.beanvalidationsandbox.domain.IntDomain;
import com.example.beanvalidationsandbox.domain.StringDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

public class BeanValidationTest {

    /**
     * 通常のBeanValidationの検証
     */
    @Test
    public void test_defaultBeanValidation() {

        // 検証に失敗するように値を設定する。
        GeneralBean generalBean = new GeneralBean();
        generalBean.setStringField("");
        generalBean.setIntField(120);
        generalBean.setDateField(Date.valueOf(LocalDate.now().plusDays(1)));

        validate(generalBean);
    }

    /**
     * ネストしたクラスに対するValidation
     */
    @Test
    public void test_nestFieldBeanValidation() {

        // 検証に失敗する値を設定する。
        NestFieldBean nestFieldBean = new NestFieldBean();
        nestFieldBean.setNestClass(nestFieldBean.new NestClass());

        validate(nestFieldBean);
    }

    /**
     * ドメインクラスをフィールドに持つBeanの検証。
     * ドメインというか、カスタムバリデーションの検証。
     */
    @Test
    public void test_domainFieldBeanValidation() {

        // 検証に失敗する値を設定する。
        DomainFieldBean domainFieldBean = new DomainFieldBean();
        domainFieldBean.setStringDomain(new StringDomain().setValue("1234"));
        domainFieldBean.setIntDomain(new IntDomain().setValue(100));
        domainFieldBean.setCollectionDomain(new CollectionDomain().setValue(Arrays.asList(1, 2)));

        validate(domainFieldBean);
    }

    /**
     *  チェックを実施するメソッド
     */
    public <T> void validate(T target) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> result = validator.validate(target);

        result.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
    }


}
