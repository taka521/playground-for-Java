package commons.beanutils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ToBean {

    private List<String> strings;
    private LocalDateTime dateTime;
    private int[] ints;

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int[] getInts() {
        return ints;
    }

    public void setInts(int[] ints) {
        this.ints = ints;
    }

    @Override
    public String toString() {
        return "ToBean{" + "strings=" + strings + ", dateTime=" + dateTime + ", ints=" + Arrays.toString(ints) + '}';
    }
}
