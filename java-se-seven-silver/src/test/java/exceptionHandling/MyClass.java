package exceptionHandling;

public class MyClass {
    private static String name;

    static {
        name.length(); // ExceptionInInitializerErrorが発生するぞい。
    }

}
