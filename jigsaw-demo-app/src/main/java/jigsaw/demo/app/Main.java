package jigsaw.demo.app;

import jigsaw.demo.app.domain.Memo;

public class Main {

    public static void main(String[] args) {

        Memo<Integer, String> memo = Memo.emptyMemo();

        memo.resist(Integer.valueOf(1), "Hello World");
        memo.resist(Integer.valueOf(2), "こんにちは");

        memo.update(Integer.valueOf(1), "Hello World".toUpperCase());

        System.out.println(memo.get(Integer.valueOf(1)));
        System.out.println(memo.size());

        memo.deleteAll();
        System.out.println(memo.size());

    }
}
