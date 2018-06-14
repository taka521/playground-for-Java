package com.example.cipher;

/**
 * 暗号利用モードを表す列挙体
 */
public enum CipherMode {
    ECB("ECB", "Electronic Codebook", false),
    CBC("CBC", "Cipher Block Chaining", true),
    CFB("CFB", "Cipher Feedback", true),
    OFB("OFB", "Output Feedback", true),
    CTR("CTR", "Counter", false);

    /** 暗号利用モード名 */
    private final String name;

    /** 説明 */
    private final String description;

    /** 初期ベクトル要否 */
    private final boolean needIV;

    CipherMode(final String name, final String description, final boolean needIV) {
        this.name = name;
        this.description = description;
        this.needIV = needIV;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isNeedIV() {
        return needIV;
    }
}
