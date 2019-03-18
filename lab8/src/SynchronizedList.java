import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedList {
    public List list = Collections.synchronizedList(new ArrayList<Object>());

    boolean contains(Object o){
        return list.contains(o);
    }

    boolean remove(Object o){
        return list.remove(o);
    }

    boolean add(Object o){
        return list.add(o);
    }
}
