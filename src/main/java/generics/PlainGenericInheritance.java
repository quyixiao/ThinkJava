package generics;//: generics/PlainGenericInheritance.java


/****
 *
 * 409
 *
 *
 *
 *
 * 如果 不使用自限定，它包含一个abstract方法，这个方法将的接受一个泛型类型，
 * 并产生具有这个泛型类型参数的返回值，在这个类的非abstract方法中，调用这个abstract方法，
 * 并返回其结果，继承这个自限定类型，并测试所产生的类。
 *
 *
 *
 *
 *
 * 1
 *
 * @param <T>
 */
class GenericSetter<T> { // Not self-bounded
    void set(T arg) {
        System.out.println("GenericSetter.set(Base)");
    }
}

class DerivedGS extends GenericSetter<Base> {
    void set(Derived derived) {
        System.out.println("DerivedGS.set(Derived)");
    }
}

public class PlainGenericInheritance {
    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();
        DerivedGS dgs = new DerivedGS();



        dgs.set(derived);
        dgs.set(base); // Compiles: overloaded, not overridden!


    }
} /* Output:
DerivedGS.set(Derived)
GenericSetter.set(Base)
*///:~
