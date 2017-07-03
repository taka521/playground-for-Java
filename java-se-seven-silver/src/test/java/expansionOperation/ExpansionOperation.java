package expansionOperation;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpansionOperation {

    @Test
    public void test_抽象クラスを抽象クラスで継承() {

        abstract class AbsClassA {}
        abstract class AbsClassB extends AbsClassA {}

        // 抽象クラスを抽象クラスで継承するのは可能。
    }

    @Test
    public void test_共変戻り値() {

        abstract class ClassA {
            abstract Number getNumber();
        }

        class ClassB extends ClassA {

            @Override
            Integer getNumber() {
                return Integer.valueOf(0);
            }

        }

        ClassA a = new ClassB();
        a.getNumber();

        // サブクラスでメソッドをオーバライドする場合、
        // そのメソッドの戻り値と同じ型 or サブクラスを戻すことができる。
        // これを "共変戻り値" という。
    }

    @Test
    public void test_親クラスと子クラスで同一のフィールドを定義していた場合() {

        class ClassA {
            String value;
        }

        class ClassB extends ClassA {
            String value;

            public void setValue(final String value){
                super.value = value;
            }
        }

        ClassB classB = new ClassB();
        classB.value = "ClassB";

        ClassA classA = ((ClassA) classB);

        assertTrue("ClassB".equals(classB.value));
        assertNull(classA.value);
        assertTrue("ClassB".equals(((ClassB) classA).value));

        // 親クラスとサブクラスに同一フィールドが定義されていた場合、
        // どちらに値を設定するかは、フィールド参照の場合、変数の型によって決まる。
        // 例えば上記の場合、ClassBのvalueに対して文字列を設定している。
        // そのため、ClassAのvalueには値が設定されないため、nullのままである。
        // 親のフィールドを使用する場合には、super.value とすることでアクセスできる。
    }
}
