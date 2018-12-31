//: generics/E12_NewTest2.java
/****************** Exercise 12 *****************
 * Repeat the previous exercise using explicit type
 * specification.
 ************************************************/
package generics;

import net.util.New;

import java.util.List;
import java.util.Set;


/****
 *
 *
 * 363页
 * 使用显示的类型说明来重复前一个练习
 */
public class E12_NewTest2 {
    static void f(List<
            SixTuple<Byte, Short, String, Float, Double, Integer>> l) {
        l.add(
                new SixTuple<Byte, Short, String, Float, Double, Integer>(
                        (byte) 1, (short) 1, "A", 1.0F, 1.0, 1));
        System.out.println(l);
    }

    static void g(Set<Sequence<String>> s) {
        s.add(new Sequence<String>(5));
        System.out.println(s);
    }

    public static void main(String[] args) {
        f(New.<SixTuple<Byte, Short, String, Float, Double, Integer>>
                list());
        g(New.<Sequence<String>>set());
    }
}