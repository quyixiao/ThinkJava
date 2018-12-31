package reusing;//: reusing/Beetle.java
// The full process of initialization.

import static net.mindview.util.Print.print;

/****
 * 145页
 *
 *
 * 1
 *
 *
 *  在设计一个系统时，目标应该是找到或创建某些类，其中每个类都有具体的用途，而且既
 *  不会太大，包夜太多的功能而无法复用，也不会太少，不添加其他的功能就无法使用，如果你
 *  的设计变得过于复杂，通过将现有类拆分为更小的部分而添加更多的对象，通常会有所帮助
 *
 */
class Insect {
    private int i = 9;
    protected int j;

    Insect() {
        print("i = " + i + ", j = " + j);
        j = 39;
    }

    private static int x1 =
            printInit("static Insect.x1 initialized");

    static int printInit(String s) {
        print(s);
        return 47;
    }
}

public class Beetle extends Insect {
    private int k = printInit("Beetle.k initialized");

    public Beetle() {
        print("k = " + k);
        print("j = " + j);
    }

    private static int x2 =
            printInit("static Beetle.x2 initialized");

    public static void main(String[] args) {
        print("Beetle constructor");
       Beetle b = new Beetle();
    }
} /* Output:
static Insect.x1 initialized
static Beetle.x2 initialized
Beetle constructor
i = 9, j = 0
Beetle.k initialized
k = 47
j = 39
*///:~
