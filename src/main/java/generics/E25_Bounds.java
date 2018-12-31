//: generics/E25_Bounds.java
/****************** Exercise 25 *****************
 * Create two interfaces and a class that implements
 * both. Create two generic methods, one whose argument
 * parameter is bounded by the first interface and
 * one whose argument parameter is bounded by the
 * second interface. Create an instance of the class
 * that implements both interfaces, and show that
 * it can be used with both generic methods.
 ************************************************/
package generics;


/*****
 *
 *
 *
 * 389页
 *      创建两个接口和一个实现了这两个接口的类，创建两个泛型方法，其中一个参数
 *  边界被限定为第一个接口，而另一个的参数边界被限定为第二个接口，创建实现了这
 *  两个接口的类型，并展示它可以用于这两个泛型方法
 *
 *
 */
interface Low {
    void c();

    void d();
}

class TopLowImpl implements Top, Low {
    public void a() {
        System.out.println("Top::a()");
    }

    public void b() {
        System.out.println("Top::b()");
    }

    public void c() {
        System.out.println("Low::c()");
    }

    public void d() {
        System.out.println("Low::d()");
    }
}

public class E25_Bounds {
    static <T extends Top> void top(T obj) {
        obj.a();
        obj.b();
    }

    static <T extends Low> void low(T obj) {
        obj.c();
        obj.d();
    }

    public static void main(String[] args) {
        TopLowImpl tli = new TopLowImpl();
        top(tli);
        low(tli);
    }
}