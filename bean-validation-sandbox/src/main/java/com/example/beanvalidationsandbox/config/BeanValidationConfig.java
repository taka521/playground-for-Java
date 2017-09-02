package com.example.beanvalidationsandbox.config;

import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;

import javax.validation.Validator;

@Configuration
public class BeanValidationConfig {

    private MessageSource messageSource;

    public BeanValidationConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Bean
    public OptionalValidatorFactoryBean optionalValidatorFactoryBean() {
        OptionalValidatorFactoryBean factoryBean = new OptionalValidatorFactoryBean();
        MessageInterpolatorFactory interpolatorFactory = new MessageInterpolatorFactory();

        factoryBean.setMessageInterpolator(interpolatorFactory.getObject());
        factoryBean.setValidationMessageSource(messageSource);

        return factoryBean;
    }

    @Bean
    public Validator validator() {
        return optionalValidatorFactoryBean();
    }

}
