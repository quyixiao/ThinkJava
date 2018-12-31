//: annotations/E09_HashMapTest.java
/****************** Exercise 09 *****************
 * Write basic @Unit tests for HashMap.
 ***********************************************/
package annotations;

import net.atunit.Test;
import net.mindview.util.OSExecute;

import java.util.HashMap;

public class E09_HashMapTest {
    HashMap<String, Integer> testObject =
            new HashMap<String, Integer>();

    @Test
    void initialization() {
        assert testObject.isEmpty();
    }

    @Test
    void _containsValue() {
        testObject.put("one", 1);
        assert testObject.containsValue(1);
    }

    @Test
    void _containsKey() {
        testObject.put("one", 1);
        assert testObject.containsKey("one");
    }

    @Test
    void _remove() {
        testObject.put("one", 1);
        testObject.remove("one");
        assert testObject.isEmpty();
    }

    @Test
    void _get() {
        testObject.put("one", 1);
        assert testObject.get("one") == 1;
    }

    @Test
    void _size() {
        testObject.put("one", 1);
        testObject.put("two", 2);
        assert testObject.size() == 2;
    }

    @Test
    void _clear() {
        testObject.put("one", 1);
        assert !testObject.isEmpty();
        testObject.clear();
        assert testObject.isEmpty();
    }

    public static void main(String[] args) throws Exception {
        OSExecute.command("java " +
                " net.mindview.atunit.AtUnit E09_HashMapTest");
    }
}