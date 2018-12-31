//: annotations/E07_LinkedListTest2.java
/****************** Exercise 07 *****************
 * Use the inheritance approach to modify Exercise 6.
 ***********************************************/
package annotations;

import net.atunit.Test;
import net.mindview.util.OSExecute;

import java.util.LinkedList;

public class E07_LinkedListTest2
        extends LinkedList<String> {
    @Test
    void initialization() {
        assert isEmpty();
    }

    @Test
    void _contains() {
        add("one");
        assert contains("one");
    }

    @Test
    void _remove() {
        add("one");
        remove("one");
        assert isEmpty();
    }

    public static void main(String[] args) throws Exception {
        OSExecute.command("java " +
                " net.mindview.atunit.AtUnit E07_LinkedListTest2");
    }
}