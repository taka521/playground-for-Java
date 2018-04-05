package spring.beans.inner;

import java.util.List;

public class BeanB {

    private String stringValue;
    private List<Integer> integerList;
    private InnerB inner;

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

    public InnerB getInner() {
        return inner;
    }

    public void setInner(final InnerB inner) {
        this.inner = inner;
    }

    public static class InnerB {

        private String innerString;

        public String getInnerString() {
            return innerString;
        }

        public void setInnerString(final String innerString) {
            this.innerString = innerString;
        }
    }

}
