//: arrays/ParameterizedArrayType.java
package arrays;


/**
 * 440
 * <p>
 * 注意，使用参数化方法而不使用参数化类的方便之处在于：你不必为需要应用的每一种不同
 * 的类型都使用一个参数化实例化这个类，并且你可以将其定义为静态的，当然不是总是选择
 * 参数化方法，而不使用参数化类
 * <p>
 * 正如上例中说的那样，不能创建泛型数组这一说法是不准确的，诚然，编译器确实不让你实例
 * 化泛型数组，但是可以创建泛型数组的引用
 * <p>
 * <p>
 *
 *
 *
 *
 *
 * 1
 *
 * @param <T>
 */
class ClassParameter<T> {
    public T[] f(T[] arg) {
        return arg;
    }
}

class MethodParameter {
    public static <T> T[] f(T[] arg) {
        return arg;
    }
}

public class ParameterizedArrayType {
    public static void main(String[] args) {
        Integer[] ints = {1, 2, 3, 4, 5};
        Double[] doubles = {1.1, 2.2, 3.3, 4.4, 5.5};
        Integer[] ints2 = new ClassParameter<Integer>().f(ints);
        Double[] doubles2 = new ClassParameter<Double>().f(doubles);
        ints2 = MethodParameter.f(ints);
        doubles2 = MethodParameter.f(doubles);
    }
} ///:~
