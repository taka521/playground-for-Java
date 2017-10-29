package com.example.text;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.text.CharacterPredicate;
import org.apache.commons.text.RandomStringGenerator;

public class Main {

    // 半角英数のみ
    private static final CharacterPredicate ALPHANUMERIC = v -> CharUtils.isAsciiAlphanumeric((char) v);

    public static void main(String[] args) {

        for(int i = 1; i <= 10; i++){
            System.out.printf("%02d回目；%s%n", i, generateRandomString(15));
        }

    }

    // ランダムな文字列を生成する
    public static String generateRandomString(final int length) {
        return new RandomStringGenerator.Builder() // ビルダーを生成
                .withinRange('0', 'z')             // 生成する文字の範囲を指定
                .filteredBy(ALPHANUMERIC)          // 生成される文字を抽出する条件を指定
                .build()                           // RandomStringGenerator をインスタンス化
                .generate(length);                 // 指定した長さの文字列を取得
    }

}
