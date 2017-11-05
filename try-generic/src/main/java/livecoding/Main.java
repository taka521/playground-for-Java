package livecoding;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        nonGenericStyle();
        genericStyle();

    }

    static void nonGenericStyle() {


    }

    static void genericStyle() {

    }

    public static <E> E head(List<E> list){
        E e = list.get(0);
        return e;
    }
}
