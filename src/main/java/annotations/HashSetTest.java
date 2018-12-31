//: annotations/HashSetTest.java
package annotations;

import net.atunit.Test;
import net.mindview.util.OSExecute;

import java.util.HashSet;


/****
 * 638页
 * 如果采用继承的方式，可能会更简单，并且也没用一些其他的约束。
 *
 *
 *
 *
 */
public class HashSetTest {
    HashSet<String> testObject = new HashSet<String>();

    @Test
    void initialization() {
        assert testObject.isEmpty();
    }

    @Test
    void _contains() {
        testObject.add("one");
        assert testObject.contains("one");
    }

    @Test
    void _remove() {
        testObject.add("one");
        testObject.remove("one");
        assert testObject.isEmpty();
    }

    public static void main(String[] args) throws Exception {
        OSExecute.command(
                "java net.mindview.atunit.AtUnit HashSetTest");
    }
} /* Output:
annotations.HashSetTest
  . initialization
  . _remove
  . _contains
OK (3 tests)
*///:~
