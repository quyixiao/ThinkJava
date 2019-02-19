//: enumerated/RoShamBo.java
// Common tools for RoShamBo examples.
package enumerated;

import net.mindview.util.Enums;


/**
 * 616页
 * <p>
 * 然后，我们定义两个status方法，static可以避免显式的指明参数类型，第一个是match()方法，它会为一个
 * Competitor对象调用compete()方法，并与另一个Competitor对象作比较，在这个例子中，我们看到，match()
 * 方法的参数需要是Competitor<T>类型，但是在play() 方法中，类型参数必须同时是Enum类型，因为它将在Enums.random()中
 * 使用，和Competitor类型，因为它将被传递给match()方法
 * <p>
 * <p>
 * <p>
 * <p>
 * play()方法没有将类型参数作为返回值类型，因此，似乎我们应该在Class<T>中使用通配符来代替上面的参数声明，然而，通配符不能
 * 扩展多个基类，所以我们必须采用以上的表达式。
 */
public class RoShamBo {
    public static <T extends Competitor<T>> void match(T a, T b) {
        System.out.println(a + " vs. " + b + ": " + a.compete(b));
    }

    public static <T extends Enum<T> & Competitor<T>> void play(Class<T> rsbClass, int size) {
        for (int i = 0; i < size; i++) {
            match(Enums.random(rsbClass), Enums.random(rsbClass));
        }
    }
} ///:~
