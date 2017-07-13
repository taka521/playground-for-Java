package jigsaw.demo.app.utils;

import org.junit.Test;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * {@link CollectionUtils} のテストクラス
 */
public class CollectionUtilsTest {

    @Test
    public void test_isEmptyにnullが渡された場合(){
        assertThat(CollectionUtils.isEmpty(null), is(true));
    }

    @Test
    public void test_isEmptyに空のコレクションが渡された場合(){
        assertThat(CollectionUtils.isEmpty(EMPTY_LIST), is(true));
    }

    @Test
    public void test_isEmptyに要素が存在するコレクションが渡された場合(){
        assertThat(CollectionUtils.isEmpty(List.of("one")), is(false));
    }

    @Test
    public void test_isNotEmptyにnullが渡された場合(){
        assertThat(CollectionUtils.isNotEmpty(null), is(false));
    }

    @Test
    public void test_isNotEmptyに空のコレクションが渡された場合(){
        assertThat(CollectionUtils.isNotEmpty(EMPTY_LIST), is(false));
    }

    @Test
    public void test_isNotEmptyに要素が存在するコレクションが渡された場合(){
        assertThat(CollectionUtils.isNotEmpty(List.of("one")), is(true));
    }

}
