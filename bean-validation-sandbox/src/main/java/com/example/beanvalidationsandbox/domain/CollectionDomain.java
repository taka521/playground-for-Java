package com.example.beanvalidationsandbox.domain;

import java.util.Collection;

/**
 * コレクションを扱うドメインクラス
 */
public class CollectionDomain implements Domain<Collection<?>> {

    private Collection<?> value;

    public CollectionDomain setValue(Collection<?> value) {
        this.value = value;
        return this;
    }

    @Override
    public Collection<?> getValue() {
        return this.value;
    }
}
