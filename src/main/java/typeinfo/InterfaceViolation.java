package typeinfo;//: typeinfo/InterfaceViolation.java
// Sneaking around an interface.

import typeinfo.interfacea.A;


/***
 *
 *
 * 346
 *
 * 1
 *
 */
class B implements A {
    public void f() {
        System.out.println("============f=========");
    }

    public void g() {
    }
}

public class InterfaceViolation {
    public static void main(String[] args) {
        A a = new B();
        a.f();
        // a.g(); // Compile error
        System.out.println(a.getClass().getName());
        if (a instanceof B) {
            B b = (B) a;
            b.g();
        }
    }
} /* Output:
B
*///:~
