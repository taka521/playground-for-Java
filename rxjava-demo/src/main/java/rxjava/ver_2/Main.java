package rxjava.ver_2;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {

        /**
         * 100ミリ秒ごとに0から始まるデータを通知するFlowable。<br/>
         * なお、通知できないデータは破棄するようにする。（BackpressureMode.DROPを指定した場合と同じ）
         */
        Flowable<Long> flowable = Flowable.interval(100L, TimeUnit.MILLISECONDS).onBackpressureDrop();

        // 非同期でデータを受け取るようにし、バッファサイズは 1 とする。
        // バッファサイズは Subscription#request に渡すリクエストの数と意味合いが一緒。
        flowable.observeOn(Schedulers.computation(), false, 2).subscribe(new DisposableSubscriber<Long>() {

            @Override
            public void onNext(Long aLong) {
                // データの生産 > データの消費 となるように、300ミリスリープさせる。
                try {
                    Thread.sleep(300L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.exit(1);
                }

                System.out.printf("[%s]%d%n", Thread.currentThread().getName(), aLong);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.printf("[%s]完了しました！%n", Thread.currentThread().getName());
            }
        });

        Thread.sleep(2000L);

    }
}
