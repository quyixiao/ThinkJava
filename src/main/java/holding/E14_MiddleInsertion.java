//: holding/E14_MiddleInsertion.java
/****************** Exercise 14 *****************
 * Create an empty LinkedList<Integer>. Using a
 * ListIterator, add Integers to the List by always
 * inserting them in the middle of the List.
 ***********************************************/
package holding;

import java.util.LinkedList;
import java.util.ListIterator;


/******
 *
 *
 *229页
 *  创建一个空间的LinkedList<Integer>,通过使用ListIterator，将若干个Interget插入这个List中，插入时，总是到List的中间
 *
 */
public class E14_MiddleInsertion {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        ListIterator<Integer> it = list.listIterator();
        for (int i = 1; i <= 10; i++) {
            it.add(i);
            if (i % 2 == 0)
                it.previous();
        }
        System.out.println(list);
    }
}