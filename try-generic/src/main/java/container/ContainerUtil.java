package container;

import java.util.function.Function;

public class ContainerUtil {

    private ContainerUtil() {}

    public static <E> void print(Container<? extends E> container) {
        System.out.println(container.getValue());
    }

    public static <E, R> Container<R> convert(Container<E> c, Function<? super E, ? extends R> function) {
        R convertedValue = function.apply(c.getValue());
        return new Container<>(convertedValue);
    }

}
