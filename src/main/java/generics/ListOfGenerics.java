package generics;//: generics/ListOfGenerics.java

import java.util.ArrayList;
import java.util.List;


/****
 * 354页
 *
 * 1
 * @param <T>
 */
public class ListOfGenerics<T> {
    private List<T> array = new ArrayList<T>();

    public void add(T item) {
        array.add(item);
    }

    public T get(int index) {
        return array.get(index);
    }
} ///:~
