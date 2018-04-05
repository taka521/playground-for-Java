package commons.beanutils;

import commons.beanutils.inner.BeanA;
import commons.beanutils.inner.BeanB;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class CommonsBeanUtilsTest {

    private List<String> strings = null;
    private LocalDateTime dateTime = null;
    private int[] ints = null;

    @Before
    public void before() {
        strings = Stream.of("1", "2", "3", "4", "5").collect(Collectors.toList());
        dateTime = LocalDateTime.of(2018, 1, 2, 10, 20, 30);
        ints = new int[]{10, 11, 12, 13, 14, 15};
    }

    /**
     * {@link BeanUtils#copyProperties(Object, Object)} は、シャローコピーである。<br/>
     * ディープコピーではないので、コピー元のオブジェクトが変更されれば、コピー先にも影響が出る。
     */
    @Test
    public void copyPropertiesのシャローコピー確認() throws Exception {
        // テストデータ用意
        FromBean from = new FromBean();
        from.setStrings(strings);
        from.setDateTime(dateTime);
        from.setInts(ints);
        ToBean to = new ToBean();

        // BeanUtils#copyProperties を実行
        BeanUtils.copyProperties(to, from);

        // コピー元のデータを書き換える
        from.getStrings().remove(0);
        from.getInts()[0] = 100;
        // DateTimeAPIはimmutableなライブラリなので、あんまり意味ない
        from.setDateTime(from.getDateTime().plusDays(1));

        // チェック
        assertThat(to.getStrings()).as("要素数が4であること")
                .hasSize(4)
                .as("要素は2, 3, 4, 5の文字列であること")
                .containsSequence("2", "3", "4", "5")
                .as("0の文字列は含んでないこと")
                .doesNotContain("0");

        assertThat(to.getInts()).as("要素は11, 12, 13, 14, 15, 100であること")
                .containsSequence(100, 11, 12, 13, 14, 15)
                .as("10は含んでいないこと")
                .doesNotContain(10);

        assertThat(to.getDateTime()).matches(dt -> {
            assertThat(dt.getYear()).as("2018年であること").isEqualTo(2018);
            assertThat(dt.getMonth()).as("1月であること").isEqualTo(Month.of(1));
            assertThat(dt.getDayOfMonth()).as("2日であること").isEqualTo(2);
            return true;
        });

    }

    @Test
    public void インナークラスをフィールドに持つ異なるオブジェクトの場合01() throws Exception {

        BeanA from = new BeanA();
        from.setStringValue("==String==");
        from.setIntegerList(new ArrayList<>(Arrays.asList(1, 2, 3)));

        BeanA.InnerA inner = new BeanA.InnerA();
        inner.setInnerString("InnerString");
        from.setInner(inner);

        BeanB to = new BeanB();
        BeanUtils.copyProperties(from, to);

        assertThat(to.getStringValue()).as("").isEqualTo("==String==");
        assertThat(to.getInner()).as("型が一致しない場合はnullであること").isNull();
    }

    @Test
    public void インナークラスをフィールドに持つ異なるオブジェクトの場合02() throws Exception {

        BeanA from = new BeanA();
        from.setStringValue("==String==");
        from.setIntegerList(new ArrayList<>(Arrays.asList(1, 2, 3)));
        BeanA.InnerA inner = new BeanA.InnerA();
        inner.setInnerString("InnerString");
        from.setInner(inner);

        BeanB to = new BeanB();
        BeanUtilsBean beanUtilsBean = BeanUtilsBean2.getInstance();
        beanUtilsBean.copyProperties(from, to);

        assertThat(to.getInner()).as("型が一致しない場合はnullであること").isNull();
    }

}
