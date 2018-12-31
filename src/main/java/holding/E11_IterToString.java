//: holding/E11_IterToString.java
/****************** Exercise 11 *****************
 * Write a method that uses an Iterator to step
 * through a Collection and print the toString()
 * of each object in the container. Fill all the
 * different types of Collections with objects and
 * apply your method to each container.
 ***********************************************/
package holding;

import java.util.*;

/******
 *  227页
 *  写一个方法，使用Iterator遍历Collection，并打印容器中每个对象的toString()
 *  填充各种类型的Collection，然后对其使用此方法
 *
 *
 *
 * 1
 *
 *
 */
public class E11_IterToString {
    public static void printToStrings(Iterator<?> it) {
        while (it.hasNext())
            System.out.println(it.next().toString());
    }

    @SuppressWarnings("unchecked")
    public static void main(String args[]) {
        List<Collection<String>> ca =
                Arrays.<Collection<String>>asList(
                        new ArrayList<String>(),
                        new LinkedList<String>(),
                        new HashSet<String>(),
                        new TreeSet<String>());
        for (Collection<String> c : ca)
            E04_MovieNameGenerator.fill(c);
        for (Collection<String> c : ca) {
            printToStrings(c.iterator());
            System.out.println("=====================");

        }

    }
}