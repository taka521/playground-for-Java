package spring.beans;

import java.util.List;

public class YourBean {

    private String stringValue;
    private List<Integer> integerList;

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
}
