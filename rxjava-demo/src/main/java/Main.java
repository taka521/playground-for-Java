import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class Main {

    /** データが通知された場合のAction1 */
    private static final Action1<String> ON_NEXT = item -> System.out.printf("[%s]%s%n", Thread.currentThread().getName(), item);

    /** エラー通知時のAction1 */
    private static final Action1<Throwable> ON_ERROR = Throwable::printStackTrace;

    /** 完了通知時のAction0 */
    private static final Action0 ON_COMPLETED = () -> System.out.printf("[%s]完了しました。%n", Thread.currentThread().getName());

    public static void main(String[] args) {

        // "Hello" と "World" を通知するObservable
        // justメソッドでは、購読解除時の対応も完了の通知も実装されたObservableが生成される。
        Observable<String> observableGreeting = Observable.just("Hello", "World");

        // Observableを購読し、処理を行う。
        // subscribeメソッドにはいくつか種類があり、今回は関数インターフェースを受け取るメソッドを使用する。
        observableGreeting.subscribe(ON_NEXT, ON_ERROR, ON_COMPLETED);

    }
}
