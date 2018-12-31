package generics;//: generics/SimpleQueue.java
// A different kind of container that is Iterable

import java.util.Iterator;
import java.util.LinkedList;


/***
 * 422
 *
 *
 * 1
 * @param <T>
 */
public class SimpleQueue<T> implements Iterable<T> {
    private LinkedList<T> storage = new LinkedList<T>();

    public void add(T t) {
        storage.offer(t);
    }

    public T get() {
        return storage.poll();
    }

    public Iterator<T> iterator() {
        return storage.iterator();
    }
} ///:~
