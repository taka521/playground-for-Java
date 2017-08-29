package rxjava.ver_2.operations;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * サンプル用のSubscriberです。
 *
 * @param <T> 通知するデータの型
 */
public class DebugSubscriber<T> extends ResourceSubscriber<T> {

    /** ラベル */
    private String label;

    public DebugSubscriber() {
        super();
    }

    public DebugSubscriber(String label) {
        super();
        this.label = label;
    }

    @Override
    public void onNext(T t) {
        String threadName = Thread.currentThread().getName();
        if (label == null) {
            System.out.printf("[%s]%s%n", threadName, t);
        } else {
            System.out.printf("[%s][%s]%s%n", threadName, this.label, t);
        }
    }

    @Override
    public void onError(Throwable t) {
        String threadName = Thread.currentThread().getName();
        if (label == null) {
            System.out.printf("[ERROR][%s]%s%n", threadName, t);
        } else {
            System.out.printf("[ERROR][%s][%s]%s%n", threadName, this.label, t);
        }
    }

    @Override
    public void onComplete() {
        String threadName = Thread.currentThread().getName();
        if (label == null) {
            System.out.printf("[%s]%s%n", threadName, "完了");
        } else {
            System.out.printf("[%s][%s]%s%n", threadName, this.label, "完了");
        }

    }
}
