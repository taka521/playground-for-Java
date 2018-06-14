package com.example.cipher;

import java.util.function.Supplier;

class Tuple<L, R> {

    final L left;
    final R right;

    Tuple(L left, R right) {
        this.left = left;
        this.right = right;
    }

    static <L> Tuple<L, Throwable> of(Supplier<L> supplier) {
        L left;
        Throwable right;
        try {
            left = supplier.get();
            right = null;
        } catch (Exception e) {
            left = null;
            right = e;
        }
        return new Tuple<>(left, right);
    }
}
