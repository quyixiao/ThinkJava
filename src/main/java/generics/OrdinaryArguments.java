package generics;//: generics/OrdinaryArguments.java


/****
 * 参数类型不能随子类型发生变化
 *
 *
 *
 * 1
 *
 */
class OrdinarySetter {
    void set(Base base) {
        System.out.println("OrdinarySetter.set(Base)");
    }
}

class DerivedSetter extends OrdinarySetter {
    void set(Derived derived) {
        System.out.println("DerivedSetter.set(Derived)");
    }
}

public class OrdinaryArguments {
    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();
        DerivedSetter ds = new DerivedSetter();


        ds.set(derived);
        ds.set(base); // Compiles: overloaded, not overridden!


    }
} /* Output:
DerivedSetter.set(Derived)
OrdinarySetter.set(Base)
*///:~
