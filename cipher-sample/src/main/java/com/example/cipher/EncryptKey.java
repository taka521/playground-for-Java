package com.example.cipher;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 暗号鍵クラス
 *
 * <p>暗号鍵と初期ベクトルを保持します。
 */
public class EncryptKey {

    private static final Charset ENCODE = StandardCharsets.UTF_8;
    private static final int KEY_LENGTH = 16;
    private static final int IV_LENGTH = 16;
    private static final int AMOUNT_LENGTH = KEY_LENGTH + IV_LENGTH;

    private final String key;

    private EncryptKey(final String key) {
        Objects.requireNonNull(key, "key is null.");
        if (key.length() != AMOUNT_LENGTH) {
            throw new IllegalArgumentException(String.format("key is not %d length.", AMOUNT_LENGTH));
        }

        this.key = key;
    }

    public static EncryptKey of(final String key) {
        return new EncryptKey(key);
    }

    public String getKey() {
        return this.key.substring(0, KEY_LENGTH);
    }

    public String getIv() {
        return this.key.substring(IV_LENGTH);
    }

    public byte[] getKeyBytes() {
        return this.getKey().getBytes(ENCODE);
    }

    public byte[] getIvBytes() {
        return this.getIv().getBytes(ENCODE);
    }

}
