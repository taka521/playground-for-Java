package com.example.beanvalidationsandbox.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MessageConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private MessageSource messageSource;

    @Bean
    public OptionalValidatorFactoryBean validator() {
        OptionalValidatorFactoryBean optionalValidatorFactoryBean = new OptionalValidatorFactoryBean();
        optionalValidatorFactoryBean.setValidationMessageSource(messageSource);

        return optionalValidatorFactoryBean;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

}
