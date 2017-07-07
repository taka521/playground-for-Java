package rxjava.ver_1;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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
        // observeOnメソッドで、データを受け取った時にどのスレッド上で処理させるかを設定できる。
        // observeOnメソッドには、Schedulerを渡す。
        // Schedulerはスレッドプールにスレッドがあればそこから取得し、無ければ新たに生成します。（生成したスレッドは、処理後にプールされる）
        observableGreeting.observeOn(Schedulers.computation()).subscribe(ON_NEXT, ON_ERROR, ON_COMPLETED);

        // メインスレッド名を出力
        System.out.printf("[%s]subscribed!%n", Thread.currentThread().getName());

        // 非同期で処理されていることを確認するためにメインスレッドの処理を止める
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
