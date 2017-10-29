package com.example;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.text.CharacterPredicate;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.Test;

public class RandomStringGeneratorTest {

    static final CharacterPredicate NUMBER_ONLY = v -> 48 <= v && v <= 57;
    static final CharacterPredicate UPPERCASE_ALPHA = v -> 65 <= v && v <= 90;
    static final CharacterPredicate LOWERCASE_ALPAHA = v -> 97 <= v && v <= 122;

    @Test
    public void testRandomStringGenerator() {
        final CharacterPredicate ALPHANUMERIC = v -> CharUtils.isAsciiAlphanumeric((char) v);

        final String result = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(NUMBER_ONLY, UPPERCASE_ALPHA, LOWERCASE_ALPAHA)
                .build()
                .generate(20);

        p(result);
    }

    public static void p(Object o) {
        System.out.println(o);
    }

}
