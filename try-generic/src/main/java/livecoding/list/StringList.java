package livecoding.list;

import java.util.ArrayList;
import java.util.List;

public class StringList {

    private final List list = new ArrayList();

    public boolean add(String s) {
        return list.add(s);
    }

    public String get(int index) {
        return (String) list.get(index);
    }

    public int size(){
        return list.size();
    }
}
