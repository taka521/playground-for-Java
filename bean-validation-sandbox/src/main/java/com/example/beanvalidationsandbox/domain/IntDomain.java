package com.example.beanvalidationsandbox.domain;

/**
 * 整数を扱うドメインクラス
 */
public class IntDomain implements Domain<Integer> {

    private Integer value;

    public IntDomain setValue(Integer value) {
        this.value = value;
        return this;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
