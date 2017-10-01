package container;

/**
 * 任意型の値を保持するコンテナクラス
 *
 * @param <E> 　保持する要素型
 */
public class Container<E> {

    private E value;

    public Container() {
    }

    public Container(final E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void setValue(final E value) {
        this.value = value;
    }
}
