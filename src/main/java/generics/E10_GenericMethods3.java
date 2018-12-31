//: generics/E10_GenericMethods3.java
/****************** Exercise 10 *****************
 * Modify the previous exercise so that one of
 * f()'s arguments is non-parameterized.
 ************************************************/
package generics;

/******
 *
 *
 * 362页
 *  修改前一个练习，将方法f()的其中一个参数修改为非参数化的类型
 *
 *
 * 1
 */
public class E10_GenericMethods3 {
    public <A, B> void f(A a, B b, Boolean c) {
        System.out.println(a.getClass().getName());
        System.out.println(b.getClass().getName());
        System.out.println(c.getClass().getName());
    }


    public <A, B> void f(A a,Boolean c, B b ) {
        System.out.println(a.getClass().getName());
        System.out.println(b.getClass().getName());
        System.out.println(c.getClass().getName());
    }




    public static void main(String[] args) {
        E10_GenericMethods3 gm = new E10_GenericMethods3();
        gm.f("", 1, true);
        gm.f(1.0, 1.0F, false);
        gm.f('c', gm, true);
    }
}