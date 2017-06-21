package switchExpression;


import org.junit.Test;

public class SwitchExpression {

    /**
     * switch文の構文は
     *
     * switch( 条件式 ){
     *     case 定数1:
     *         break;
     *     case 定数2:
     *         break;
     *     default:
     * }
     *
     * となっており、この条件式の値として返却できる型が決まっている。
     * 具体的には
     *
     * ・char
     * ・byte
     * ・short
     * ・int
     * ・Character
     * ・Byte
     * ・Short
     * ・Integer
     * ・String
     * ・Enum
     *
     * の10個。
     * 簡単に言うと、
     *
     * ・int以下の整数とそのラッパー
     * ・文字と文字列
     * ・列挙型
     *
     * の３つということになる。
     *
     */
    @Test
    public void test_switchの条件式が戻せる型(){

        long l = 0L;

        /*
        switch(l){
            case 0L:
                System.out.println("0L");
                break;
            default:
                System.out.println("");
        }
        */

        // longの他に、doubleやfloatもダメ。

    }

}
