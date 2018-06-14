package com.example.cipher;

import org.junit.Test;

import javax.crypto.Cipher;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CipherUtilsTest {

    @Test
    public void createCipher_正常終了() {

        // データ準備
        final CipherUtils.EncryptMode encryptMode = CipherUtils.EncryptMode.ENCRYPT;
        final CipherAlgorithm algorithm = CipherAlgorithm.of()
                .setSystem(CryptoSystem.AES)
                .setMode(CipherMode.CBC)
                .setPadding(CipherPadding.PKCS5Padding);
        final EncryptKey encryptKey = EncryptKey.of("xhnCSSG6LRzgfgrpDWi7HNz8wAzdPwe8");

        // 実行
        final Cipher cipher = CipherUtils.createCipher(encryptMode, algorithm, encryptKey);

        // 結果チェック
        assertThat(cipher.getAlgorithm()).isEqualTo(algorithm.getAlgorithm());
        assertThat(cipher.getIV()).isEqualTo(encryptKey.getIvBytes());
        assertThat(cipher.getBlockSize()).isEqualTo(16);
    }


    @Test
    public void convert_正常終了() {
        // データ準備
        final CipherUtils.EncryptMode encryptMode = CipherUtils.EncryptMode.ENCRYPT;
        final CipherAlgorithm algorithm = CipherAlgorithm.of()
                .setSystem(CryptoSystem.AES)
                .setMode(CipherMode.CBC)
                .setPadding(CipherPadding.PKCS5Padding);
        final EncryptKey encryptKey = EncryptKey.of("xhnCSSG6LRzgfgrpDWi7HNz8wAzdPwe8");
        final Cipher encryptCipher = CipherUtils.createCipher(encryptMode, algorithm, encryptKey);

        // 実行
        final byte[] source = "abcde".getBytes();
        final byte[] encryptSource = CipherUtils.convert(encryptCipher, source);
        final Cipher decryptCipher = CipherUtils.createCipher(CipherUtils.EncryptMode.DECRYPT, algorithm, encryptKey);
        final byte[] decryptSource = CipherUtils.convert(decryptCipher, encryptSource);

        // 結果チェック
        assertThat(decryptSource).as("復号化したデータが、暗号化前のデータと一致すること。").containsSequence(source);
    }

    @Test
    public void 様々なアルゴリズムで暗号化と復号化してみる() {
        final List<CipherAlgorithm> algorithms = this.createCipherAlgorithm();
        final EncryptKey encryptKey = EncryptKey.of("xhnCSSG6LRzgfgrpDWi7HNz8wAzdPwe8");
        final byte[] source = "abcedfg".getBytes();

        algorithms.stream()
                .map(algorithm -> Tuple.of(
                        () -> CipherUtils.createCipher(CipherUtils.EncryptMode.ENCRYPT, algorithm, encryptKey)))
                .peek(tuple -> {
                    System.out.println(String.format("left:%s,right:%s", tuple.left, tuple.right));
                })
                .filter(tuple -> tuple.left != null)
                .collect(Collectors.toList());

    }

    private List<CipherAlgorithm> createCipherAlgorithm() {
        return Arrays.stream(CipherMode.values())
                .flatMap(mode -> Arrays.stream(CipherPadding.values()).map(p -> new Tuple<>(mode, p)))
                .map(tuple -> CipherAlgorithm.of()
                        .setSystem(CryptoSystem.AES)
                        .setMode(tuple.left)
                        .setPadding(tuple.right))
                .collect(Collectors.toList());
    }

}