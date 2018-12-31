package generics;//: generics/UseList2.java

import java.util.List;


/***
 *
 *
 * 403
 *
 *
 *
 * 由于擦除原因，重载方法将产生相同的类型签名
 *
 *
 *
 * 与此不同的是，当被擦除的参数不能产生唯一的参数列表时，必须提供明显有区别的方法名
 *
 *
 *
 *
 * 1
 *
 *
 * @param <W>
 * @param <T>
 */
public class UseList2<W, T> {
    void f1(List<T> v) {
    }

    void f2(List<W> v) {


    }
} ///:~
