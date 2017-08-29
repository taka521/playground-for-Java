package rxjava.ver_2.operations;

import io.reactivex.Flowable;

import java.util.Arrays;

import static java.lang.Thread.*;
import static java.util.concurrent.TimeUnit.*;

/**
 * <p>
 * RxJavaのオペレータについて。<br/>
 * オペレータ とは、
 * <ul>
 * <li>FlowableやObservableを生成する。</li>
 * <li>FlowableやObservableが通知するデータの選別を行う。</li>
 * </ul>
 * などを行い、新たなFlowableやObservableなどを生成するメソッドのこと。
 * 具体的な例を挙げると、データをフィルタリングする<code>filter</code>メソッド、
 * データを変換する<code>map</code>メソッドがあり、これらは基本的にFlowableまたはObservableを返します。
 */
public class RxJavaOperation {

    public static void main(String... args) throws Exception {

        //Flowableの生成に関するオペレータ();
        //通知するデータを制限するオペレータ();
        通知するデータを変換するオペレータ();
    }

    public static void Flowableの生成に関するオペレータ() throws Exception {

        // justメソッドは、引数に渡されたデータを順番に通知するFlowableを生成するメソッド。
        // すべてのデータを通知したら、完了を通知（onComplete）します。
        Flowable<String> justFlowable = Flowable.just("one", "two", "three");
        justFlowable.subscribe(new DebugSubscriber<>("justFlowable"));

        // fromArrayメソッドは、引数に渡された配列の要素を、順に通知するFlowableを生成するメソッド。
        // すべてのデータを通知したら、完了を通知（onComplete）します。
        Flowable<String> fromArrayFlowable = Flowable.fromArray("A", "B", "C");
        fromArrayFlowable.subscribe(new DebugSubscriber<>("fromArrayFlowable"));

        // fromIterableメソッドは、引数に渡されたイテレーブルの要素を順に通知するFlowableを生成するメソッド。
        // すべてのデータを通知したら、完了を通知（onComplete）します。
        Flowable<String> fromIterableFlowable = Flowable.fromIterable(Arrays.asList("1", "2", "3"));
        fromIterableFlowable.subscribe(new DebugSubscriber<>("fromIterableFlowable"));

        // interbalメソッドは、指定した通知間隔で 0 から始まる値のデータを通知するFlowableを生成するメソッド。
        // このメソッドで生成されたFlowableは、デフォルトで呼び出し元とは異なる別スレッド上で実行されます。
        // なお、interbalメソッドから生成されたFlowableは完了することがないため、takeメソッド等で通知数を制限する必要があります。
        Flowable<Long> interbalFlowable = Flowable.interval(1000L, MILLISECONDS).take(3);
        interbalFlowable.subscribe(new DebugSubscriber<>("interbalFlowable"));

        // timerメソッドは、呼び出されてから指定した時間だけ待機後、0 だけを通知して完了するFlowableを生成するメソッド。
        // 生成されるFlowableは、デフォルトでは呼び出し元とは異なるスレッド上で実行される。
        Flowable<Long> timerFlowable = Flowable.timer(100L, MILLISECONDS);
        timerFlowable.subscribe(new DebugSubscriber<>("timerFlowable"));

        sleep(5000L);
    }

    public static void 通知するデータを制限するオペレータ() throws Exception {

        // データを生成するFlowable
        Flowable<Long> interbalFlowable = Flowable.interval(100L, MILLISECONDS);

        // filterメソッドは引数に Predicate を受け取り、条件に一致するデータのみを通知するメソッドです。
        Flowable<Long> filterFlowable = interbalFlowable.filter(data -> data % 2 == 0);
        filterFlowable.subscribe(new DebugSubscriber<>("filterFlowable"));
        sleep(500L);

        /*
            == 実行結果 ====================================
            [RxComputationThreadPool-1][filterFlowable]0
            [RxComputationThreadPool-1][filterFlowable]2
            [RxComputationThreadPool-1][filterFlowable]4
            ===============================================
         */

        // takeメソッドは、指定したデータ数や期間に達するまで受け取ったデータを通知するメソッドです。
        Flowable<Long> takeCountFlowable = interbalFlowable.take(10); // 通知する個数を指定
        takeCountFlowable.subscribe(new DebugSubscriber<>("takeCountFlowable"));
        sleep(1500L);

        /*
            == 実行結果 =======================================
            [RxComputationThreadPool-1][takeCountFlowable]0
            [RxComputationThreadPool-1][takeCountFlowable]1
            [RxComputationThreadPool-1][takeCountFlowable]2
            [RxComputationThreadPool-1][takeCountFlowable]3
            [RxComputationThreadPool-1][takeCountFlowable]4
            [RxComputationThreadPool-1][takeCountFlowable]5
            [RxComputationThreadPool-1][takeCountFlowable]6
            [RxComputationThreadPool-1][takeCountFlowable]7
            [RxComputationThreadPool-1][takeCountFlowable]8
            [RxComputationThreadPool-1][takeCountFlowable]9
            [RxComputationThreadPool-1][takeCountFlowable]完了
            ===================================================
         */

        Flowable<Long> takeTimeFlowable = interbalFlowable.take(1000L, MILLISECONDS); // 通知する期間を指定
        takeTimeFlowable.subscribe(new DebugSubscriber<>("takeTimeFlowable"));
        sleep(1500L);

        /*
            == 実行結果 =======================================
            [RxComputationThreadPool-2][takeTimeFlowable]0
            [RxComputationThreadPool-2][takeTimeFlowable]1
            [RxComputationThreadPool-2][takeTimeFlowable]2
            [RxComputationThreadPool-2][takeTimeFlowable]3
            [RxComputationThreadPool-2][takeTimeFlowable]4
            [RxComputationThreadPool-2][takeTimeFlowable]5
            [RxComputationThreadPool-2][takeTimeFlowable]6
            [RxComputationThreadPool-2][takeTimeFlowable]7
            [RxComputationThreadPool-2][takeTimeFlowable]8
            [RxComputationThreadPool-2][takeTimeFlowable]9
            [RxComputationThreadPool-2][takeTimeFlowable]完了
            ==================================================
         */

        // skipメソッドは、指定したデータスや期間に達した後に通知を行うメソッドです。
        Flowable<Long> skipCountFlowable = interbalFlowable.skip(5);
        skipCountFlowable.subscribe(new DebugSubscriber<>("skipCountFlowable"));
        sleep(1000L);

        /*
            == 実行結果 =======================================
            [RxComputationThreadPool-1][skipCountFlowable]5
            [RxComputationThreadPool-1][skipCountFlowable]6
            [RxComputationThreadPool-1][skipCountFlowable]7
            [RxComputationThreadPool-1][skipCountFlowable]8
            [RxComputationThreadPool-1][skipCountFlowable]9
            ==================================================
         */

        Flowable<Long> skipTimeFlowable = interbalFlowable.skip(500L, MILLISECONDS);
        skipTimeFlowable.subscribe(new DebugSubscriber<>("skipTimeFlowable"));
        sleep(1000L);

        /*
            == 実行結果 ======================================
            [RxComputationThreadPool-2][skipTimeFlowable]5
            [RxComputationThreadPool-2][skipTimeFlowable]6
            [RxComputationThreadPool-2][skipTimeFlowable]7
            [RxComputationThreadPool-2][skipTimeFlowable]8
            [RxComputationThreadPool-2][skipTimeFlowable]9
            =================================================
         */

        // distinctUntilChangedメソッドは、直前に通知したデータと等しいデータを連続で通知しようとしている場合に、
        // そのデータを除外して通知してくれるオペレータ。（あくまで直前のデータとの比較なので、重複があっても連続してなければ意味ない）
        Flowable<String> distinctUntilChangedFlowable = Flowable.just("A", "b", "b", "C", "A").distinctUntilChanged();
        distinctUntilChangedFlowable.subscribe(new DebugSubscriber<>("distinctUntilChangedFlowable"));

        /*
            == 実行結果 ==================================
            [main][distinctUntilChangedFlowable]A
            [main][distinctUntilChangedFlowable]b
            [main][distinctUntilChangedFlowable]C
            [main][distinctUntilChangedFlowable]A
            [main][distinctUntilChangedFlowable]完了
            ==============================================
         */

        // throttleWithTimeoutメソッドは、Flowableからデータを受け取った後、
        // 指定した期間内に別のデータを受け取らなければ再度同じデータを通知するメソッド。
        // コードは省略。

    }

    public static void 通知するデータを変換するオペレータ() throws Exception {

        Flowable<String> stringFlowable = Flowable.just("1", "2", "3", "4", "5");

        // mapメソッドは元となるFlowableから通知されたデータを変換し、その変換したデータを通知するオペレータ。
        // なお、null や 複数のデータを返してはいけない。
        Flowable<Integer> mapFlowable = stringFlowable.map(Integer::valueOf);
        mapFlowable.subscribe(new DebugSubscriber<>("mapFlowable"));


        // flatMapメソッドは、mapメソッドのように元のデータを変換し、通知するオペレータです。
        // mapメソッドと異なる点は、受け取った単一のデータから複数のデータを持ったFlowableを返すことが可能である点です。
        // 例えば以下は、文字列を1つ渡して、その文字列から2つの文字列を生成して通知する Flowable<String> を返しています。
        Flowable<String> flatMapFlowable = Flowable.just("A", "", "B", "C", "", "", "D").flatMap(data -> {

            // データが空文字の場合は通知させないように、空のFlowableを返す。
            if ("".equals(data)) return Flowable.empty();

            // 受け取ったデータの大文字と小文字を100ミリ秒語に通知する。
            return Flowable.just(data.toUpperCase(), data.toLowerCase()).delay(300L, MILLISECONDS);
        });

        flatMapFlowable.subscribe(new DebugSubscriber<>("flatMapFlowable"));

        sleep(1000L);
    }

}
