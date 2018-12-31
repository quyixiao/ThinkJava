//: net/mindview/util/FiveTuple.java
package net.mindview.util;


/*****
 *
 * 增加类型参数是一件简单的事情
 *
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 * @param <E>
 */
public class FiveTuple<A, B, C, D, E> extends FourTuple<A, B, C, D> {
    public final E fifth;

    public FiveTuple(A a, B b, C c, D d, E e) {
        super(a, b, c, d);
        fifth = e;
    }

    public String toString() {
        return "(" + first + ", " + second + ", " + third + ", " + fourth + ", " + fifth + ")";
    }
}



///:~
