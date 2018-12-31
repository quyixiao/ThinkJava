//: generics/E20_Bounds.java
/****************** Exercise 20 *****************
 * Create an interface with two methods, and a class
 * that implements that interface and adds another
 * method. In another class, create a generic method
 * with an argument type that is bounded by the
 * interface, and show that the methods in the
 * interface are callable inside this generic method.
 * In main(), pass an instance of the implementing
 * class to the generic method.
 ************************************************/
package generics;


/****
 *
 *
 * 375页
 *      创建一个具有两个方法的接口，以及一个实现了这个接口并添加了另一个方法的类
 *  在另一个类中创建一个泛型方法，它的参数类型由这个接口定义了边界，并展示该接口的方法
 *  在这个泛型方法中都是可调用的，在main()方法中传递一个实现类的实例给这个泛型方法
 *
 */
interface Top {
    void a();

    void b();
}

class CombinedImpl implements Top {
    public void a() {
        System.out.println("Top::a()");
    }

    public void b() {
        System.out.println("Top::b()");
    }

    public void c() {
        System.out.println("CombinedImpl::c()");
    }
}

public class E20_Bounds {
    static <T extends Top> void f(T obj) {
        obj.a();
        obj.b();
        // c() is not part of an interface
        // obj.c();
    }

    public static void main(String[] args) {
        f(new CombinedImpl());
    }
}