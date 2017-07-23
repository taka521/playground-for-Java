package basicAPI;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayListAPI {

    @Test
    public void test_set() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("1");
        stringArrayList.add("2");

        stringArrayList.set(1, "3");

        assertThat(stringArrayList.get(1), is("3"));

        /*
            ArrayList#set メソッドは、要素の置き換えを行うメソッド。
            index は 0 から開始。
            範囲外へアクセスした場合、IndexOutOfBoundsExceptionがスローされる。
         */
    }

    @Test
    public void test_remove() {

        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        for (String s1 : list) {
            if ("B".equals(s1)) {
                list.remove(s1);
            } else {
                System.out.println(s1);
            }
        }

    }

    @Test
    public void test_remove_例外がスローされる場合(){

        List<String> list2 = new ArrayList<>();
        list2.add("A");
        list2.add("B");
        list2.add("C");
        list2.add("D");
        list2.add("E");

        for (String s2 : list2) {
            if ("C".equals(s2)) list2.remove(s2);
        }

        /*
            このコードは例外がスローされる。
            しかし、末尾 - 1の位置の要素を削除する場合には例外がスローされない。
            上記の例だと、"D".equals(s) なら例外がスローされない。
         */
    }


}
