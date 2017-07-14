package jigsaw.demo.app.domain;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.tuple.Tuples;

/**
 * メモ実装クラス
 *
 * @param <K> キーの型
 * @param <V> メモの型
 */
final class MemoImpl<K, V> implements Memo<K, V> {

    /** メモ格納 */
    private final MutableMap<K, V> memos;

    /** コンストラクタ */
    private MemoImpl() {
        this.memos = Maps.mutable.empty();
    }

    /**
     * <p>ファクトリメソッド
     *
     * @return 空のMemoインスタンス
     */
    static <K, V> Memo<K, V> of() {
        return new MemoImpl<>();
    }

    @Override
    public void resist(final K key, final V memo) {
        this.memos.add(Tuples.pair(key, memo));
    }

    @Override
    public V update(final K key, final V memo) {
        return memos.add(Tuples.pair(key, memo));
    }

    @Override
    public void delete(final K key) {
        memos.reject(K::equals);
    }

    @Override
    public void deleteAll() {
        memos.clear();
    }

    @Override
    public V get(final K key) {
        return memos.get(key);
    }

    @Override
    public int size() {
        return memos.size();
    }
}