package typeinfo;//: typeinfo/ClassInitialization.java

import java.util.Random;


/***
 * 如果一个类static域不是final的ble2.staticNonFinal，那么在对它访问时，总是要求在它被读取之前，要先进行链
 * 接（为这个域分配存储空间），和初始化，初始化该存储空间，就像在对Initable2.staticNonFinal的访问中所看到的
 * 那样
 *
 *
 * 1
 */
class Initable {

    /***
     * 编译期常量
     *
     */
    static final int staticFinal = 47;
    static final int staticFinal2 =
            ClassInitialization.rand.nextInt(1000);

    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {
    static int staticNonFinal = 147;

    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    static int staticNonFinal = 74;

    static {
        System.out.println("Initializing Initable3");
    }
}

public class ClassInitialization {
    public static Random rand = new Random(47);

    public static void main(String[] args) throws Exception {
        Class initable = Initable.class;
        System.out.println("After creating Initable ref");
        // Does not trigger initialization:
        System.out.println(Initable.staticFinal);
        // Does trigger initialization:
        System.out.println(Initable.staticFinal2);
        // Does trigger initialization:
        System.out.println(Initable2.staticNonFinal);
        Class initable3 = Class.forName("typeinfo.Initable3");
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);
    }
} /* Output:
After creating Initable ref
47
Initializing Initable
258
Initializing Initable2
147
Initializing Initable3
After creating Initable3 ref
74
*///:~
