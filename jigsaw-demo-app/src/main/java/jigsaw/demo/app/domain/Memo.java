package jigsaw.demo.app.domain;

/**
 * メモインターフェース
 *
 * @param <K> キーの型
 * @param <V> メモの型
 */
public interface Memo<K, V> {

    /**
     * 空のメモインスタンスを作成します。
     *
     * @param <K> キーの型
     * @param <V> メモの型
     *
     * @return 空のメモインスタンス
     */
    static <K, V> Memo<K, V> emptyMemo() {
        return MemoImpl.of();
    }

    /**
     * <p>メモを登録します。<br/>
     * 登録時にはキーとメモの内容を指定してください。
     *
     * @param key  キー
     * @param memo メモ
     */
    void resist(K key, V memo);

    /**
     * <p>登録済みのメモを更新します。
     *
     * @param key  キー
     * @param memo 更新するメモ
     *
     * @return 更新後のメモ
     */
    V update(K key, V memo);

    /**
     * <p>指定されたキーのメモを削除します。
     *
     * @param key キー
     */
    void delete(K key);

    /**
     * <p>登録済みメモを全て削除します。
     */
    void deleteAll();

    /**
     * <p>キーを元に、メモを取得します。
     *
     * @param key キー
     *
     * @return メモ
     */
    V get(K key);

    /**
     * 登録されているメモの数を返します。
     *
     * @return 登録数
     */
    int size();

}
