import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MySynchronizedList {
    public List list = new ArrayList<ListElement>();

    boolean contains(Object o){
        ListIterator<ListElement> it = list.listIterator();
        it.next().lock.lock();
        while(it.hasNext()){
            it.previous();
            if(it.next().value.equals(o)){
                it.previous().lock.unlock();
                return true;
            }
            it.previous().lock.unlock();
            it.next().lock.lock();
        }
        return false;
    }

    boolean remove
}
