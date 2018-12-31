package generics;//: generics/SelfBoundingMethods.java


/****
 *
 *
 *
 * 407
 *
 *
 *
 * 这可以防止这个方法被应用于除上述形式的自限定参数之外的任何事物上
 *
 *
 * 1
 *
 *
 */
public class SelfBoundingMethods {
    static <T extends SelfBounded<T>> T f(T arg) {
        return arg.set(arg).get();
    }

    public static void main(String[] args) {
        A a = f(new A());
    }
} ///:~
