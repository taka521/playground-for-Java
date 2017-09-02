package com.example.beanvalidationsandbox.domain;

/**
 * 文字列を扱うドメインクラス
 */
public class StringDomain implements Domain<String> {

    private String value;

    public StringDomain setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
