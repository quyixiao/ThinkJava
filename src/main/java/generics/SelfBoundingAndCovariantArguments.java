package generics;//: generics/SelfBoundingAndCovariantArguments.java


/****
 *
 * 409
 *
 *
 * 编译器不能识别将基类型当作类型能数传递给set()的尝试，因为没有任何方法具有这样的签名，
 * 实际上，这引进参数己经被覆盖
 *
 *
 * 1
 *
 * @param <T>
 */
interface SelfBoundSetter<T extends SelfBoundSetter<T>> {
    void set(T arg);
}

interface Setter extends SelfBoundSetter<Setter> {
}

public class SelfBoundingAndCovariantArguments {
    void testA(Setter s1, Setter s2, SelfBoundSetter sbs) {
        s1.set(s2);
        //s1.set(sbs); // Error:
        // set(Setter) in SelfBoundSetter<Setter>
        // cannot be applied to (SelfBoundSetter)
    }
} ///:~
