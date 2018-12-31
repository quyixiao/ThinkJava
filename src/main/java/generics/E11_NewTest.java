//: generics/E11_NewTest.java
/****************** Exercise 11 *****************
 * Test New.java by creating your own classes and
 * ensuring that New will work properly with them.
 ************************************************/
package generics;

import net.util.New;

import java.util.List;
import java.util.Set;

/*****
 *
 *
 *
 *  363页
 * 创建自己的若干个类测试New.java，并确保new可以正确地与它们一起工作
 *
 *
 *
 * 1
 */
public class E11_NewTest {
    public static void main(String[] args) {
        List<SixTuple<Byte, Short, String, Float, Double, Integer>> list = New.list();
        list.add(new SixTuple<Byte, Short, String, Float, Double, Integer>(
                        (byte) 1, (short) 1, "A", 1.0F, 1.0, 1));
        System.out.println(list);
        Set<Sequence<String>> set = New.set();
        set.add(new Sequence<String>(5));
        System.out.println(set);
    }
}