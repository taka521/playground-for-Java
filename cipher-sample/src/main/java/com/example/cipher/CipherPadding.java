package com.example.cipher;

/**
 * パディング方式を表す列挙体
 */
public enum CipherPadding {
    NoPadding("NoPadding"),
    ZeroBytePadding("ZeroBytePadding"),
    PKCS5Padding("PKCS5Padding"),
    PKCS7Padding("PKCS7Padding");
    
    /** パディング名 */
    private final String name;

    CipherPadding(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
