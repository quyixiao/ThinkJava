package holding;//: holding/ApplesAndOrangesWithoutGenerics.java
// Simple container example (produces compiler warnings).
// {ThrowsException}

import java.util.ArrayList;

/****
 *      这个实例还表明，如果不需要使用每个元素的索引，你可以使用foreach语法来选择List中的
 * 每个元素
 *
 */
class Apple {
    private static long counter;
    private final long id = counter++;

    public long id() {
        return id;
    }
}

class Orange {
}

public class ApplesAndOrangesWithoutGenerics {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ArrayList apples = new ArrayList();
        for (int i = 0; i < 3; i++)
            apples.add(new Apple());
        // Not prevented from adding an Orange to apples:
        apples.add(new Orange());
        for (int i = 0; i < apples.size(); i++)
            ((Apple) apples.get(i)).id();
        // Orange is detected only at run time
    }
} /* (Execute to see output) *///:~
