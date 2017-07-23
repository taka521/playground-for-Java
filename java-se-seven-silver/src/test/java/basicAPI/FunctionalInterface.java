package basicAPI;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterface {

    // Java8で提供された関数interfaceは以下。

    /** 引数を受け取り結果を戻さない「消費者」 */
    private final Consumer<String> consumer = s -> System.out.println(s);

    /** 引数を受け取らず、結果を戻す「供給者」 */
    private final Supplier<String> supplier = () -> "Hello World";

    /** 引数を受け取り、評価する「断定」 */
    private final Predicate<String> predicate = s -> "a".equals(s);

    /** 引数を受け取り、指定された型の値を返す「処理」 */
    private final Function<String, Integer> function = s -> s.length();
}
