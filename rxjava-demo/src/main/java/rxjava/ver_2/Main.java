package rxjava.ver_2;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Main {
    public static void main(String[] args) {

        // あいさつの文字列を通知する、Flowable（Publisher）の生成
        Flowable<String> flowable = Flowable.create(flowableEmitter -> {

            String[] datas = {"Hello", "World"};
            for (String item : datas) {
                // 購読がキャンセルされていなければデータを通知
                if (flowableEmitter.isCancelled()) return;
                flowableEmitter.onNext(item);
            }

            flowableEmitter.onComplete(); // 完了したことを通知
        }, BackpressureStrategy.BUFFER); // 超過したデータはバッファする。


        // Subscriberの処理を別スレッドで実行させる。
        flowable.observeOn(Schedulers.computation()).subscribe(new Subscriber<String>() {

            /** データ数のリクエスト及び、購読のキャンセルを行うオブジェクト */
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                this.subscription = s;
                this.subscription.request(1L); // 受け取るデータ数をリクエスト
            }

            @Override
            public void onNext(String s) {
                System.out.printf("[%s]%s%n", Thread.currentThread().getName(), s); // 受け取ったデータを出力
                this.subscription.request(1L); // 次に受け取るデータ数をリクエスト
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace(); // エラー通知時の処理
            }

            @Override
            public void onComplete() {
                System.out.printf("[%s]完了しました！", Thread.currentThread().getName()); // 完了通知時の処理
            }
        });

        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
