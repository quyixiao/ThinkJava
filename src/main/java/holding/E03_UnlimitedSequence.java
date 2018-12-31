//: holding/E03_UnlimitedSequence.java
/****************** Exercise 3 ******************
 * Modify innerclasses/Sequence.java so you
 * can add any number of elements to it.
 ***********************************************/
package holding;

import java.util.ArrayList;
import java.util.List;


/******
 *
 *220页
 *
 * 练习3
 *  修改一个innerclasses/Sequence.java ，使你可以向其中添加任意数量的元素
 *
 *
 */

interface Selector {
    boolean end();

    Object current();

    void next();
}


class UnlimitedSequence {
    private final List<Object> items =
            new ArrayList<Object>();

    public void add(Object x) {
        items.add(x);
    }

    private class SequenceSelector implements Selector {
        private int i;

        public boolean end() {
            return i == items.size();
        }

        public Object current() {
            return items.get(i);
        }

        public void next() {
            if (i < items.size()) i++;
        }
    }

    public Selector selector() {
        return new SequenceSelector();
    }
}

public class E03_UnlimitedSequence {
    public static void main(String[] args) {
        UnlimitedSequence sequence = new UnlimitedSequence();
        for (int i = 0; i < 10; i++)
            sequence.add(Integer.toString(i));
        Selector selector = sequence.selector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}