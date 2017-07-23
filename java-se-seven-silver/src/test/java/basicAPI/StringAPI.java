package basicAPI;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * java.util.String に関するAPI。
 */
public class StringAPI {

    private final String string = "abcde";

    @Test
    public void test_charAt() {

        assertThat('b', is(string.charAt(1)));

        try {
            string.charAt(7); // 範囲外へのアクセスは例外がスローされる。
            fail();
        } catch (StringIndexOutOfBoundsException e) {
            // done
        }

        // String#charAt(int index)は、文字列から指定された位置のcharを取得するメソッド。
        // index は 0から開始される。
        // 文字列の範囲外にアクセスしようとした場合には、StringIndexOutOfBoundsExceptionがスローされる。

    }

    @Test
    public void test_substring() {

        assertThat(string.substring(1, 3), is("bc")); // abcde から dc を切り出す。
        assertThat(string.substring(2), is("cde"));   // abcde から cde を切り出す。

        /*
            String#substring(int beginIndex, int endIndex) は、文字列から指定された文字を切り出すメソッド。
            indexは 0 から開始される。
            例えば、substring(1, 3) とした場合、2番目の文字から3番目の文字までを切り出すことになる。
            String#substring(int beginIndex) メソッドも存在し、こちらは末尾まで取得する。

            以下のように文字列を区切ると分かりやすい。

            | a | b | c | d | e |
            0   1   2   3   4   5

            "abcde".substring(1, 3) とした場合、開始は 1 なので a と b の間となる。
            終了は 3 なので、c と d の間。
            で、この 1 と 3 の間にある文字列 bc が切り出されるイメージ。
        */

    }

    @Test
    public void test_trim() {

        String s = " a b c d \t ";
        assertThat(s.trim(), is("a b c d"));

        /*
          String#trim() メソッドは、文字列の前後の空白を除去するメソッド。
          対象となるのは、
            * 半角空白
            * タブ (\t)
            * 改行コード (\n, \r, \r\n)
            * null
          などです。
        */

    }

    @Test
    public void test_replace() {

        String target = "aaaab";
        assertThat(target.replace("aa", "b"), is("bbb"));

        /*
            String#replace(CharSequence target, CharSequence replacement) は、文字列を置換するメソッド。
            target に 置換対象の文字列、replacement に置換する文字列を指定する。
            置換は文字列の先頭から末尾まで行われる。
            String#replaceAll メソッドとの違いは、targetに指定した文字を正規表現と見なすか否かの違い。
            replaceAll の場合は正規表現として見なされる。

            replace は「１回のみの置換」で、replaceAll は「全ての文字に対する置換」ではないので注意！！

            例えば「.」を「a」に置換したい場合、
            replaceメソッドの場合は、replace(".", "a") とすればいいが、
            replaceAll メソッドは replaceAll("\.", "a") と、「.」をエスケープする必要がある。
            仮に replaceAll(".", "a") とした場合には、「.」は正規表現上 "任意の一文字" の意味なので、
            全ての文字が 「a」に置換されてしまう。
         */

    }

    @Test
    public void test_nullとの文字列結合() {

        String s = null;
        s += "null";
        assertThat(s, is("nullnull"));

        /*
            null と文字列結合する場合、null は "null" という文字列に変換される。
            なので、上記のように null のStringに対して "null" を結合した場合、
            null → "null" に置き換わり、それに対して "null" を結合するので "nullnull" となる。
         */
    }
}
