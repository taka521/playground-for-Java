package jigsaw.demo.app.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 文字列のユーティリティクラス
 */
public final class StringUtil {

    private StringUtil() {
        throw new AssertionError();
    }

    /**
     * <p>文字列が空であるか判定します。<br/>
     * 引数の値が null もしくは ブランク の場合に true、そうでなければ false を返します。
     *
     * @param s 文字列
     *
     * @return 結果
     */
    public static boolean isEmpty(final String s) {
        return StringUtils.isEmpty(s);
    }

    /**
     * <p>文字列が空でないかを判定します。<br/>
     * 引数の値が空出ない場合に true、空もしくは null の場合には false を返します。
     *
     * @param s 文字列
     *
     * @return 結果
     */
    public static boolean isNotEmpty(final String s) {
        return !isEmpty(s);
    }
}
