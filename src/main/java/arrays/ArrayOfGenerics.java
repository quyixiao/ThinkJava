//: arrays/ArrayOfGenerics.java
// It is possible to create arrays of generics.
package arrays;

import java.util.ArrayList;
import java.util.List;


/**
 * 441
 * 一旦拥有了对List<String></String>的引用，你就会看到你的将得到某些编译器检查，问题是数组是协变类型的
 * 因此List<String>也是一个object[] ,而不会有任何编译期和运行期的错误
 * 如果你知道你知道将来不会向上转型，并且需求也是比较简单的，那么你仍然可以使用创建
 * 泛型数组，它可以提供基本的编译期检查，但是事实上，泛型容器总是比泛型数组好选择
 * <p>
 * <p>
 *
 *
 *
 *
 * 1
 */
public class ArrayOfGenerics {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List<String>[] ls;
        List[] la = new List[10];
        ls = (List<String>[]) la; // "Unchecked" warning
        ls[0] = new ArrayList<String>();
        // Compile-time checking produces an error:
        // la[1] = new ArrayList<Integer>();

        // The problem: List<String> is a subtype of Object
        Object[] objects = ls; // So assignment is OK
        // Compiles and runs without complaint:
        objects[1] = new ArrayList<Integer>();

        // However, if your needs are straightforward it is
        // possible to create an array of generics, albeit
        // with an "unchecked" warning:
        List<BerylliumSphere>[] spheres = (List<BerylliumSphere>[]) new List[10];
        for (int i = 0; i < spheres.length; i++)
            spheres[i] = new ArrayList<BerylliumSphere>();
    }
} ///:~
