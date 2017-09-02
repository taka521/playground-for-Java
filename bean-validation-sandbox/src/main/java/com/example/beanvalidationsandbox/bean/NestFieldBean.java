package com.example.beanvalidationsandbox.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;

/**
 * ネストしたクラスをフィールドに持つBean
 */
@Data
public class NestFieldBean {

    // ネストしたクラスに対してバリデーションを行う場合には、
    // @Validアノテーションを注釈する。
    @Valid
    private NestClass nestClass;

    /** ネストクラス */
    public class NestClass {

        @NotEmpty
        private String string;
    }

}
