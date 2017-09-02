package com.example.beanvalidationsandbox.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * 一般的なJavaBean
 */
@Data
public class GeneralBean {

    @NotEmpty
    private String stringField;

    @Range(min = 0, max = 100)
    private int intField;

    @Past
    private Date dateField;

}
