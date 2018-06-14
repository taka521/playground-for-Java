package com.example.cipher;

/**
 * 暗号化方式を表す列挙体
 */
public enum CryptoSystem {
    DES("DES", "Data Encryption Standard", true),
    AES("AES", "Advanced Encryption Standard", true);

    /** 暗号化方式名 */
    private final String name;

    /** 説明 */
    private final String description;

    /** ブロック暗号であるか */
    private final boolean isBlock;

    CryptoSystem(final String name, final String description, final boolean isBlock) {
        this.name = name;
        this.description = description;
        this.isBlock = isBlock;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isBlock() {
        return isBlock;
    }
}
