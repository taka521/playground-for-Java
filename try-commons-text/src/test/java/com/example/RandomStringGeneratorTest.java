package com.example;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.text.CharacterPredicate;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.Test;

public class RandomStringGeneratorTest {

    @Test
    public void testRandomStringGenerator() {
        final CharacterPredicate ALPHANUMERIC = v -> CharUtils.isAsciiAlphanumeric((char) v);

        final String result = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(Character::isLetter, Character::isDigit)
                .build()
                .generate(20);

        p(result);
    }

    public static void p(Object o) {
        System.out.println(o);
    }

}
