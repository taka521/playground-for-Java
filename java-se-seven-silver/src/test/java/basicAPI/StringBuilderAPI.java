package basicAPI;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringBuilderAPI {

    @Test
    public void test_キャパシティ() {

        StringBuilder sb = new StringBuilder();
        assertThat(16, is(sb.capacity()));

        sb = new StringBuilder("abcd");
        assertThat(20, is(sb.capacity()));

        /*
            StringBuilder は、インスタンスの生成時に16文字文のバッファを確保している。
            上記のように生成時に何も指定が無ければ、16文字文のバッファ。
            初期値が指定されている場合には、初期値の文字数 + 16文字文のバッファを保持している。
         */
    }

    @Test
    public void test_append() {
        StringBuilder sb = new StringBuilder();

        sb.append(0);
        sb.append('1');
        sb.append("2");
        sb.append(true);
        sb.append(new int[]{3, 4, 5});
        sb.append("abcde", 1, 3);

        /*
            StringBuilder#append メソッドには以下のデータ型を渡すことができる。

            * プリミティブ型全て
            * String（CharSequence）
            * char配列
            * Object

            実質、なんでも入る。char以外の配列は Object として扱われる。
            プリミティブ型を渡した場合、渡した値がそのまま文字列となる。
            boolean の場合だと、true は "true" と言った具合。

            また、char配列と文字列を受け取るappendメソッドは、範囲を指定できる。
            append("abcde", 1, 3) とした場合、"bc" が切り出されて連結される。
            切り出す範囲の考え方としては、String#substring メソッドと同じ。
         */
    }

    @Test
    public void test_insert() {

        StringBuilder sb = new StringBuilder();
        sb.append("abcd");
        sb.append("ef");   // この時点で "abcdef" の文字列。

        sb.insert(2, "g"); // 2番目に "g" を挿入。

        assertThat(sb.toString(), is("abgcdef"));

        /*
            StringBuilder#insert メソッドは、内部的に保持ている文字列に対して挿入するメソッド。
            挿入位置は以下のように文字列を区切ると分かりやすい。

            | a | b | c | d | e | f |
            0   1   2   3   4   5   6

            上記は sb.insert(2, "g") なので、2の位置、
            つまり "b" と "c" の間に "g" を挿入することになる。
         */
    }

    @Test
    public void test_delete() {

        StringBuilder sb = new StringBuilder();
        sb.append("abcd");
        sb.insert(0, "A"); // 文字列は "Aabcde" となっている状態。

        sb.delete(1, 3); // 1文字目〜2文字目を消す。

        assertThat(sb.toString(), is("Acd"));

        /*

            StringBuilder#delete(int start, int end) メソッドは、
            指定された 開始位置(start) から 終了位置(end) の文字列を削除するメソッド。
            削除範囲の考え方は、文字列を区切るを分かりやすい。

            | A | a | b | c | d |
            0   1   2   3   4   5

            上記のコードだと sb.delete(1, 3) なので、開始位置は 1 。
            つまり、"A" と "a" の間。
            終了位置は 3 なので、"b" と "c" の間となり、"ab" が削除される。

            ちなみに、deleteCharAt メソッドは指定された位置の文字を削除する。
            indexは 0 開始。
              deleteCharAt(1); // ２番めの文字を削除する.
         */
    }
}
