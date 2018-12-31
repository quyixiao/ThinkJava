package operators;

import static net.mindview.util.Print.print;


/*****
 * 41 页
 * 创建一个包含一个float域的类，并且这个类来展示方法调用时的别名机制
 *
 */
public class E03_Aliasing2 {
    static void f(Integral y) {
        y.f = 1.0f;
    }

    public static void main(String[] args) {
        Integral x = new Integral();
        x.f = 2.0f;
        print("1: x.f: " + x.f);
        f(x);
        print("2: x.f: " + x.f);
    }
}