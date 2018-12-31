//: net/mindview/util/TwoTuple.java
package net.util;


/***
 * 354
 *
 *
 *
 *      仅一次方法调用就能返回多个对象，你应该经常需要这样的功能吧，可是return语句只允许返回单个对象，
 * 因此，解决办法就是创建一个对象，用它来持有想有要返回的多个对象，当然，可以在每次需要的时候，
 * 专门创建一个类来完成这样的工作，可是有了泛型，我们就能够一次性的解决该问题，以后再也不用在这个
 * 问题上浪费时间，同时，我们在编译期就能确保类型安全，
 *      这个概念称为元组，它是将一组对象直接打包存储于其中的一个单一的对象，这个容器对象台儿庄读取其中的元素，但是
 *  不允许向其存放新的对象，这个概念也称为数据传送对象或信使
 *
 *
 * @param <A>
 * @param <B>
 */
public class TwoTuple<A, B> {
    public final A first;
    public final B second;

    public TwoTuple(A a, B b) {
        first = a;
        second = b;
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }
} ///:~
