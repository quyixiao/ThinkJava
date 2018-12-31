//: generics/E09_GenericMethods2.java
/****************** Exercise 9 *****************
 * Modify GenericMethods.java so that f() accepts
 * three arguments, all of which are of a different
 * parameterized type.
 ************************************************/
package generics;

/*****
 *
 *
 * 362页
 *  修改GenericMethods.java类，使f()可以接受三个类型各不同的参数
 *
 *
 * 1
 */
public class E09_GenericMethods2 {
    public <A, B, C> void f(A a, B b, C c) {
        System.out.println(a.getClass().getName());
        System.out.println(b.getClass().getName());
        System.out.println(c.getClass().getName());
    }

    public static void main(String[] args) {
        E09_GenericMethods2 gm = new E09_GenericMethods2();
        gm.f("", 1, 1.0);
        gm.f(1.0F, 'c', gm);
    }
}