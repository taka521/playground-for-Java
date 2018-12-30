package com.example;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTrancatTest {

    /** サロゲートペアを含まない文字列 */
    private final String notInSurrogatePair = "0あ1い2う";

    /** サロゲートペア（𡢽）を含む文字列 */
    private final String inSurrogatePair = "\uD846\uDCBD0あ\uD846\uDCBD1い\uD846\uDCBD2う\uD846\uDCBD";

    @Test
    public void case001() {

        assertThat(toCodePointArray(notInSurrogatePair)).as("サロゲートペアなし").hasSize(9);

        assertThat(toCodePointArray(inSurrogatePair)).as("サロゲートペアが２文字含まれる").hasSize(11);
    }

    /**
     * 以下の処理を行います。
     *
     * <ul>
     * <li>文字列から配列に必要な長さ計算し、配列の領域を確保する。</li>
     * <li>対象位置から、正しくコードポイント値（もしくはchar型値)を取得する。</li>
     * <li>次の対象位置に移動する。</li>
     * </ul>
     *
     * @param str 対象文字列
     *
     * @return コードポイントが格納されたint配列
     */
    int[] toCodePointArray(String str) {
        int len = str.length(); // the length of str

        int[] acp = new int[str.codePointCount(0, len)];

        for (int i = 0, j = 0, cp = 0; i < len; i = str.offsetByCodePoints(i, 1)) {
            acp[j++] = str.codePointAt(i);
        }
        return acp;
    }

    @Test
    public void case002() {
        log("サロゲートペアあり=", truncateBytes(this.inSurrogatePair, StandardCharsets.UTF_8, 10));
    }


    private static String truncateBytes(String s, Charset charset, int maxBytes) {

        // 1. 入力文字を CharBuffer でラップ。
        //  内部的には StringCharBuffer が作成されている。
        final CharBuffer cb = CharBuffer.wrap(s);

        // 2. maxByte 数分のバッファ領域をもつ ByteBuffer を作成する。
        final ByteBuffer bb = ByteBuffer.allocate(maxBytes);

        // 3. 指定された文字コードのエンコーダーを作成する。
        //   CharsetEncoderを扱う際に気をつけなければならないことは以下
        //     1. encodeメソッドを呼び出す前に、必ず reset メソッドを呼び出すこと。（ただし、初回は不要）
        //     2. 追加入力がある場合は、encodeメソッドの引数 endOfInput に false を指定すること。
        //     3. 追加入力がない場合は、encodeメソッドの引数 endOfInput に true を指定すること。
        //     4. 最後は flash メソッドを呼び出し、内部状態を出力バッファへフラッシュさせること。
        //     5. CharsetEncoderはスレッドセーフではない。
        final CharsetEncoder encoder = charset.newEncoder()
                // 入力形式が正しくないエラーが発生した場合の挙動（指定文字で置き換える）
                .onMalformedInput(CodingErrorAction.REPLACE)
                // マッピング不可文字エラーが発生した場合の挙動（指定文字で置き換える）
                .onUnmappableCharacter(CodingErrorAction.REPLACE)
                // 置き換え文字の設定（デフォルトは '?' なので、実はこの設定は意味がない）
                .replaceWith(new byte[]{(byte) '?'})
                // UTF-8の場合は reset メソッドの中身は空なので意味はない。
                .reset();

        // 入力文字をエンコードし、ByteBuffer に結果を書き込む。
        // エンコードの結果、ByteBufferのバッファ領域を超えていない場合は、入力文字をそのまま戻す。
        final CoderResult cr = encoder.encode(cb, bb, true);
        if (!cr.isOverflow()) {
            return s;
        }

        // 超えている場合、以下の順で文字を取得する。
        // 1. 出力バッファへフラッシュ（flushメソッド）
        // 2. 入力バッファ側のリミットを現在の読み込み位置に設定後、現在位置を0に設定。(flipメソッド）
        // 3. 現在位置からリミットまでのバッファを文字列に変換。（toStringメソッド）
        encoder.flush(bb);
        return cb.flip().toString();
    }


    static void log(String prefix, Object o) {
        System.out.println(prefix + o);
    }
}
