package com.taka521.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BooleanConvertTest {

    static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void stringFalse() throws Exception {

        final String json = "{\"bool\":\"false\"}";
        final BoolContainer c = mapper.readValue(json, BoolContainer.class);
        assertThat(c.bool).isFalse();

        // 文字列の "false" はOK
    }

    @Test
    public void stringTrue() throws Exception {

        final String json = "{\"bool\":\"true\"}";
        final BoolContainer c = mapper.readValue(json, BoolContainer.class);
        assertThat(c.bool).isTrue();

        // 文字列の "true" はOK
    }

    @Test
    public void stringZero() throws Exception {

        try {
            final String json = "{\"bool\":\"0\"}";
            final BoolContainer c = mapper.readValue(json, BoolContainer.class);
            assertThat(c.bool).isFalse();
        } catch (Exception e) {
            // nothing to do.
        }

        // 文字列の "0" はダメ
    }

    @Test
    public void stringOne() throws Exception {

        try {
            final String json = "{\"bool\":\"1\"}";
            final BoolContainer c = mapper.readValue(json, BoolContainer.class);
            assertThat(c.bool).isTrue();
        } catch (Exception e) {
            // nothing to do.
        }

        // 文字列の "1" はダメ
    }

    @Test
    public void intZero() throws Exception {

        final String json = "{\"bool\":0}";
        final BoolContainer c = mapper.readValue(json, BoolContainer.class);
        assertThat(c.bool).isFalse();

        // 数値の 0 はOK
    }

    @Test
    public void intOne() throws Exception {

        final String json = "{\"bool\":1}";
        final BoolContainer c = mapper.readValue(json, BoolContainer.class);
        assertThat(c.bool).isTrue();

        // 数値の 1 はOK
    }

}

class BoolContainer {

    Boolean bool;

    public Boolean getBool() {
        return bool;
    }

    public void setBool(final Boolean bool) {
        this.bool = bool;
    }
}