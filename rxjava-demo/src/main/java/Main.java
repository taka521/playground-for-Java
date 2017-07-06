import rx.Observable;
import rx.Observer;

public class Main {

    public static void main(String[] args) {

        // "Hello" と "World" を通知するObservable
        Observable<String> observableGreeting = Observable.create(subscriber -> {

            // 購読解除されている場合は、処理を終了する。
            if (subscriber.isUnsubscribed()) return;

            subscriber.onNext("Hello"); // 1回目の通知
            subscriber.onNext("World"); // 2回目の通知

            // 購読解除されてない場合は、完了を通知する。
            if (!subscriber.isUnsubscribed()) subscriber.onCompleted();

        });

        // Observableを購読し、処理を行う。
        observableGreeting.subscribe(new Observer<String>() {

            // 完了通知時の処理
            @Override
            public void onCompleted() {
                String threadName = Thread.currentThread().getName();
                System.out.printf("[%s]完了しました%n", threadName);
            }

            // エラー通知時の処理
            @Override
            public void onError(final Throwable throwable) {
                throwable.printStackTrace();
            }

            // データ通知時の処理
            @Override
            public void onNext(final String item) {
                String threadName = Thread.currentThread().getName();
                System.out.printf("[%s]%s%n", threadName, item);
            }
        });

    }
}
