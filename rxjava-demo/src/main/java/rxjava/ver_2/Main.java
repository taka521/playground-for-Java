package rxjava.ver_2;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
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
        // ResourceSubscriberは、内部でonSubscribeメソッドでLong.MAX_VALUEをリクエストするようになっている抽象クラス。
        flowable.observeOn(Schedulers.computation()).subscribe(new ResourceSubscriber<String>() {

            /** 開始時間を記録する。 */
            private long startTime;

            @Override
            protected void onStart() {
                startTime = System.currentTimeMillis();
                this.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {

                // 購読開始から500ミリを超過した場合には、購読を解除する。
                long progressTime = System.currentTimeMillis() - startTime;
                if (progressTime > 500L) {
                    this.dispose();
                    System.out.println("購読を解除しました");
                    return;
                }

                // 購読を解除させるためにスリープを挟む。
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.printf("[%s]%s%n", Thread.currentThread().getName(), s); // 受け取ったデータを出力
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
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
