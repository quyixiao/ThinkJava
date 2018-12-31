//: annotations/StackLStringTest.java
// Applying @Unit to generics.
package annotations;

import net.atunit.Test;
import net.mindview.util.OSExecute;


/****
 *
 *
 * 641 页
 *
 * 要测试String版的堆栈，就让测试类继承自StackL<String>
 *
 *
 *
 *
 *
 *
 *
 */
public class StackLStringTest extends StackL<String> {
    @Test
    void _push() {
        push("one");
        assert top().equals("one");
        push("two");
        assert top().equals("two");
    }

    @Test
    void _pop() {
        push("one");
        push("two");
        assert pop().equals("two");
        assert pop().equals("one");
    }

    @Test
    void _top() {
        push("A");
        push("B");
        assert top().equals("B");
        assert top().equals("B");
    }

    public static void main(String[] args) throws Exception {
        OSExecute.command(
                "java net.mindview.atunit.AtUnit StackLStringTest");
    }
}




/* Output:
annotations.StackLStringTest
  . _push
  . _pop
  . _top
OK (3 tests)
*///:~
