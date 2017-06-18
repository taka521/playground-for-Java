package integerLiteral;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class IntegerLiteral {

    @Test
    public void test_整数リテラルの表記(){

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
}
