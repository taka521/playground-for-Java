package com.example.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * 暗号化に関するユーティリティクラス
 */
public class CipherUtils {

    private CipherUtils() {}

    public static byte[] convert(final Cipher cipher, final byte[] source) {
        try {
            return cipher.doFinal(source);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Cipher createCipher(EncryptMode encryptMode, CipherAlgorithm algorithm, EncryptKey encryptKey) {
        final SecretKeySpec keySpec = new SecretKeySpec(encryptKey.getKeyBytes(), algorithm.getCryptoName());
        final IvParameterSpec iv = new IvParameterSpec(encryptKey.getIvBytes());

        try {
            final Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm());
            if (algorithm.needsIV()) {
                cipher.init(encryptMode.id, keySpec, iv);
            } else {
                cipher.init(encryptMode.id, keySpec);
            }
            return cipher;
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate cipher.", e);
        }
    }

    /**
     * 暗号化モード
     */
    public enum EncryptMode {
        /** 暗号化 */
        ENCRYPT(1),

        /** 復号化 */
        DECRYPT(2);

        private final int id;

        EncryptMode(int id) {this.id = id;}
    }
}
