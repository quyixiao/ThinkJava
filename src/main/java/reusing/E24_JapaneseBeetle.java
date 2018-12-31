//: reusing/E24_JapaneseBeetle.java
/****************** Exercise 24 ****************
 * In Beetle.java, inherit a specific type of
 * beetle from class Beetle, following the same
 * format as the existing classes. Trace and
 * explain the output.
 ***********************************************/
package reusing;

import static net.mindview.util.Print.print;

/****
 *
 * 147页
 *
 * 在Beetle.java中，从Beetle类继承产生一个具体的类型，甲壳虫 其形式与现
 * 有的类相同，跟踪并解释其输出结果
 *
 *
 */
class JapaneseBeetle extends Beetle {
    int m = printInit("JapaneseBeetle.m initialized");

    JapaneseBeetle() {
        print("m = " + m);
        print("j = " + j);
    }

    static int x3 =
            printInit("static JapaneseBeetle.x3 initialized");
}

public class E24_JapaneseBeetle {
    public static void main(String args[]) {
        new JapaneseBeetle();
    }
}