package commons.beanutils.inner;

import java.util.List;

public class BeanA {

    private String stringValue;
    private List<Integer> integerList;
    private InnerA inner;

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(final String stringValue) {
        this.stringValue = stringValue;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(final List<Integer> integerList) {
        this.integerList = integerList;
    }

    public InnerA getInner() {
        return inner;
    }

    public void setInner(final InnerA inner) {
        this.inner = inner;
    }

    public static class InnerA {

        private String innerString;

        public String getInnerString() {
            return innerString;
        }

        public void setInnerString(final String innerString) {
            this.innerString = innerString;
        }
    }

}
