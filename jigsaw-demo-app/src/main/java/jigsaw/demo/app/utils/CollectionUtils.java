package jigsaw.demo.app.utils;

import java.util.Collection;

/**
 * コレクションのユーティリティ
 */
public final class CollectionUtils {

    private CollectionUtils() {
        throw new AssertionError();
    }

    /**
     * <p>コレクションの要素が空であるか判定します。<br/>
     * 要素が空、もしくは null の場合には true を返却し、そうでなければ false を返します。
     *
     * @param collection コレクション
     * @param <E>        要素の型
     *
     * @return 結果
     */
    public static final <E> boolean isEmpty(final Collection<E> collection) {
        return !isNotEmpty(collection);
    }

    /**
     * <p>コレクションの要素が空で無いかを判定します。<br/>
     * 要素が空でなければtrueを、空 もしくは null の場合には false を返却します。
     *
     * @param collection コレクション
     * @param <E>        要素の型
     *
     * @return 結果
     */
    public static final <E> boolean isNotEmpty(final Collection<E> collection) {
        return collection != null && !collection.isEmpty();
    }

}
