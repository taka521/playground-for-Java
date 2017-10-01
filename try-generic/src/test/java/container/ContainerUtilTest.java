package container;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ContainerUtilTest {

    @Test
    public void convert() throws Exception {

        Container<Integer> integerContainer = new Container<>(1);
        Container<String> converted = ContainerUtil.convert(integerContainer, Object::toString);

        assertThat(converted.getValue(), is("1"));
    }

}