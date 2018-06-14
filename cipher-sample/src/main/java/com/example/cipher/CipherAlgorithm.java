package com.example.cipher;

import java.util.Objects;

/**
 * アルゴリズムの情報を保持するクラス
 */
public class CipherAlgorithm {

    /** アルゴリズム */
    private CryptoSystem system;

    /** 暗号利用モード */
    private CipherMode mode;

    /** パッディング */
    private CipherPadding padding;

    private CipherAlgorithm() {}

    /**
     * @return
     */
    public static CipherAlgorithm of() {
        return new CipherAlgorithm();
    }

    /**
     * @param system
     *
     * @return
     */
    public CipherAlgorithm setSystem(final CryptoSystem system) {
        this.system = system;
        return this;
    }

    /**
     * @param mode
     *
     * @return
     */
    public CipherAlgorithm setMode(final CipherMode mode) {
        this.mode = mode;
        return this;
    }

    /**
     * @param padding
     *
     * @return
     */
    public CipherAlgorithm setPadding(final CipherPadding padding) {
        this.padding = padding;
        return this;
    }

    /**
     * @return
     */
    public String getCryptoName() {
        return this.system.getName();
    }

    /**
     * @return
     */
    public String getAlgorithm() {
        Objects.requireNonNull(this.system);
        Objects.requireNonNull(this.mode);
        Objects.requireNonNull(this.padding);

        StringBuilder sb = new StringBuilder();
        sb.append(this.system.getName()).append("/");
        sb.append(this.mode.getName()).append("/");
        sb.append(this.padding.getName());
        return sb.toString();
    }

    /**
     * 初期ベクトルを必要とするか。
     *
     * @return true:初期ベクトルが必要 / false:初期ベクトルは不要
     */
    public boolean needsIV() {
        return this.mode.isNeedIV();
    }

}
