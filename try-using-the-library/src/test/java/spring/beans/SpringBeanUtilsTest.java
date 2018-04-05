package spring.beans;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import spring.beans.inner.BeanA;
import spring.beans.inner.BeanB;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringBeanUtilsTest {

    @Test
    public void test_同一オブジェクトのコピー() {

        MyBean from = new MyBean();
        from.setStringValue("==String==");
        from.setIntegerList(new ArrayList<>(Arrays.asList(1, 2, 3)));

        MyBean to = new MyBean();
        BeanUtils.copyProperties(from, to);

        assertThat(to.getStringValue()).as("コピー元と同じ文字列であること").isEqualTo("==String==");
        assertThat(to.getIntegerList()).as("コピー元と同じ要素を保持していること").hasSize(3).contains(1, 2, 3);
    }

    @Test
    public void test_異なるオブジェクトのコピー() {

        MyBean from = new MyBean();
        from.setStringValue("==String==");
        from.setIntegerList(new ArrayList<>(Arrays.asList(1, 2, 3)));

        YourBean to = new YourBean();
        BeanUtils.copyProperties(from, to);

        assertThat(to.getStringValue()).as("コピー元と同じ文字列であること").isEqualTo("==String==");
        assertThat(to.getIntegerList()).as("コピー元と同じ要素を保持していること").hasSize(3).contains(1, 2, 3);
    }

    @Test
    public void test_インナークラスを保持する異なるオブジェクトの場合() {

        BeanA from = new BeanA();
        from.setStringValue("==String==");
        from.setIntegerList(new ArrayList<>(Arrays.asList(1, 2, 3)));
        BeanA.InnerA inner = new BeanA.InnerA();
        inner.setInnerString("InnerString");
        from.setInner(inner);

        BeanB to = new BeanB();
        BeanUtils.copyProperties(from, to);

        assertThat(to.getInner()).as("型が一致しない場合はnullであること").isNull();
    }

}
