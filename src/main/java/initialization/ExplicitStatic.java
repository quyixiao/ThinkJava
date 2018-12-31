package initialization;//: initialization/ExplicitStatic.java
// Explicit static initialization with the "static" clause.

import static net.mindview.util.Print.print;


/*****
 *
 *
 * 97页
 *  1
 *  首次生成这个类的一个对象时，或者首次访问属于那个类的静态数据成员时，即便从未生成过那个类的对象，例如
 */
class Cup {
    Cup(int marker) {
        print("Cup(" + marker + ")");
    }

    void f(int marker) {
        print("f(" + marker + ")");
    }
}

class Cups {
    static Cup cup1;
    static Cup cup2;
    static String s2;

    static {
        System.out.println("------------------------");
        cup1 = new Cup(1);
        cup2 = new Cup(2);
        s2 = "hahahahha";
    }

    Cups() {
        print("Cups()");
    }
}


public class ExplicitStatic {
    static String s2;

    static {
        System.out.println("++++++++++++++++++");
        s2 = "hahahahha";
    }


    public static void main(String[] args) {
       print("Inside main()");
        Cups cups = new Cups();
        System.out.println("----------------");
        Cups.cup1.f(99);  // (1)
        Cups.cup2.f(98);  // (1)
    }
    // static Cups cups1 = new Cups();  // (2)
    // static Cups cups2 = new Cups();  // (2)
} /* Output:
Inside main()
Cup(1)
Cup(2)
f(99)
*///:~
