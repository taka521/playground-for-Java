package exceptionHandling;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 例外操作に関するメモ書きクラス
 */
public class ExceptionHandling {

    @Test
    public void test_catch順() {

        /*
          これはコンパイルエラー

                 try {
                     throw new SubException();
                 } catch (MyException e) {
                     e.printStackTrace();
                 } catch (SubException e) {
                     e.printStackTrace();
                 }

          絶対に実行されないcatch句を定義することは禁止である。
          そのため、サブクラスよりも上位にスーパークラスの例外でcatchする ことはできない。

          ちなみに実行すると、以下のコンパイルエラーが発生する。
          Error:(15, 11) java: 例外exceptionHandling.ExceptionHandling.SubExceptionはすでに捕捉されています
        */

    }

    private class MyException extends Exception {}

    private class SubException extends MyException {}

    @Test
    public void test_catch句とfinally句でreturnが定義されていた場合() {

        assertEquals(20, getInt());

        // catch句 と finally句 の両方で return が定義されている場合、以下のような順で値が戻される。
        //
        // 1. catch句の return に指定されている値が一時領域に格納される。
        // 2. finally句の return に指定されている値で、一時領域の値を上書きする。
        // 3. 一時領域の値が呼び出し元へ返る。
        //
        // getIntメソッドでは、catch句で 10 を一時的に格納している。
        // その後 finally句 で一時変数の 10 が 20 で上書きされ、結果的に 20 が戻る。

    }

    private int getInt() {
        try {
            throw new Exception();
        } catch (Exception e) {
            return 10;
        } finally {
            return 20;
        }
    }

    @Test
    public void test_finallyで戻り値を変更できるのか() {

        assertEquals(10, getInt2());

        /*
            getInt2メソッドでは、変数valに格納した値を finally句 で書き換えている。
            結果としては、10 が返ってくる。
            というのも、戻り値として返す 変数val とメソッド内の 変数val は異なるから。
            順を追っていくと、

            1. catch句の return に定義された val を戻り値用の一時領域へ格納する。（この時は10）
            2. finally句が実行され、メソッド内の変数valが 20 に書き換えられる。
               しかし、戻り値用の一時領域に格納された val とメソッド内のvalは参照が異なるため、戻り値に変更はない。

            これはtry-catchの仕様というより、Javaのプリミティブ型の仕様によるもの。
            プリミティブ型の変数を別の変数に代入した場合、参照は異なる。

                int i = 10;
                int j = i;
                j = 20;

            なので、これが参照型の場合は同一参照となるため、
            finally句で戻り値の変数を操作したら影響を受ける。
        */
    }

    private int getInt2() {
        int val = 0;
        try {
            int[] array = {1, 2};
            System.out.print(array[3]);
        } catch (RuntimeException e) {
            val = 10;
            return val;
        } finally {
            val = 20;
        }
        return val;
    }

    @Test
    public void test_IndexOutOfBoundsExceptionとArrayIndexOutOfIndexBoundsExcepitonの違い() {

        int[] intArray = {0, 1};
        List<Integer> integerList = new ArrayList<>(Arrays.asList(0, 1));

        try {
            integerList.get(100);
        } catch (IndexOutOfBoundsException e) {
            // IndexOutOfBoundsException は、範囲外であることを示す例外。
            // この場合、指定できるindexは 0 or 1 だが、100 を指定している。
            // そのため IndexOutOfBoundsException がスローされる。
        }

        try {
            int i = intArray[100];
        } catch (ArrayIndexOutOfBoundsException e) {
            // ArrayIndexOutOfBoundsException は、範囲外へのアクセス時にスローされる例外。
            // intArray[0] or intArray[1] にしかアクセスできないが、
            // 範囲外である intArray[100] へアクセスしているため、
            // ArrayIndexOutOfBoundsException がスローされている。
        }
    }

    @Test
    public void test_ExceptionInisyaraizerErrorについて() {

        try {
            MyClass myClass = new MyClass();
        } catch (ExceptionInInitializerError e) {
            /*
               staticイニシャライザでヌルポなどが発生しうる場合には、
               ExceptionInInitializerError が発生する。
               なお、インスタンスイニシャライザの場合は、普通に例外が発生する。
               以下は、ヌルポが発生する例。

               class Hoge {
                   private String str;

                   {
                       str.length();
                   }
               }
            */
        }

    }

}
