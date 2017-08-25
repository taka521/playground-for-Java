package com.example.beanvalidationsandbox.domain;

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
