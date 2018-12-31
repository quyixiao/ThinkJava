package holding;//: holding/ForEachCollections.java
// All collections work with foreach.

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;


/****
 *
 * 到目前为止，foreach 语法主要用于数组，但是它也可以应用于任何Collection对象，你实际
 *
 * 1
 */
public class ForEachCollections {
    public static void main(String[] args) {
        Collection<String> cs = new LinkedList<String>();
        Collections.addAll(cs,
                "Take the long way home".split(" "));
        for (String s : cs)
            System.out.print("'" + s + "' ");
    }
} /* Output:
'Take' 'the' 'long' 'way' 'home'
*///:~
