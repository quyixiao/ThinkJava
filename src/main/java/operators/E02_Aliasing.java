package operators;

import static net.mindview.util.Print.print;


/*****
 *  40 页
 *  创建一个包含一个float域的类，并用这个类来展示别名机制
 *
 */
public class E02_Aliasing {
    public static void main(String[] args) {
        Integral n1 = new Integral();
        Integral n2 = new Integral();
        n1.f = 9f;
        n2.f = 47f;
        print("1: n1.f: " + n1.f + ", n2.f: " + n2.f);
        n1 = n2;
        print("2: n1.f: " + n1.f + ", n2.f: " + n2.f);
        n1.f = 27f;
        print("3: n1.f: " + n1.f + ", n2.f: " + n2.f);
    }
}