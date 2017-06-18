package integerLiteral;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class IntegerLiteral {

    /**
     * 整数リテラルのn進数別の表記方法について。
     */
    @Test
    public void test_整数リテラルの進数別表記(){

        // 基本的な整数リテラル（10進数）
        int a = 10;

        // 8進数（接頭辞：「0」）
        int b = 012;

        // 16進数（接頭辞：「0x」）
        int c = 0xA;

        // 2進数（接頭辞：「0b」）
        int d = 0b1010;

        long expected = 10L;
        assertEquals(expected, a);
        assertEquals(expected, b);
        assertEquals(expected, c);
        assertEquals(expected, d);
    }

    /**
     * 整数リテラルの「_(アンダースコア)」表記について。<br/>
     *
     * ・リテラルの先頭と末尾には記述できない。<br/>
     * ・記号の前後には記述できない。<br/>
     *
     * ここで言う「記号」とは「.(ドット)」や、floatを表す「F」、longを表す「L」、
     * 2進数を表す「0b」、16進数を表す「0x」などが含まれる。
     */
    @Test
    public void test_整数リテラルのアンダースコア表記(){

        // 基本的な記述
        int a = 10_000;

        // アンスコの個数は自由
        int b = 10__________000;

        // 2進数にも使用可能
        int c = 0b1_0;

        // 16進数にも使用可能
        int d = 0xF_0;

        // リテラルの先頭は禁止
        // int e = _123_456;

        // リテラルの末尾も禁止
        // int f = 123_456_;

        // 記号の直前は禁止。
        // int g = 1_.001;

        // 記号の直後も禁止。
        // int h = 0x_10;
    }
}
