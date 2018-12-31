//: typeinfo/FamilyVsExactType.java
// The difference between instanceof and class
package typeinfo;

import javax.xml.bind.SchemaOutputResolver;

import static net.mindview.util.Print.print;


/****
 * 333
 *
 *
 *
 * 1
 *
 *
 * test()方法使用了两中形式的instanceof作为参数来执行类型检查，然后获取Class引用，并用
 * == 和equals()来检查Class对象是否相等，使人放心的是，
 *
 *
 *
 *
 * 提供了基于构件的编程架构
 *
 *
 *  远程方法调用，需要这种分布能力有许多原因的，例如，你可以在执行一项需
 *
 */
class Base {
}

class Derived extends Base {
}

public class FamilyVsExactType {
    static void test(Object x) {
        print("Testing x of type " + x.getClass());
        print("x instanceof Base " + (x instanceof Base));
        print("x instanceof Derived " + (x instanceof Derived));
        print("Base.isInstance(x) " + Base.class.isInstance(x));
        print("Derived.isInstance(x) " +
                Derived.class.isInstance(x));
        print("x.getClass() == Base.class " +
                (x.getClass() == Base.class));
        print("x.getClass() == Derived.class " +
                (x.getClass() == Derived.class));
        print("x.getClass().equals(Base.class)) " +
                (x.getClass().equals(Base.class)));
        print("x.getClass().equals(Derived.class)) " +
                (x.getClass().equals(Derived.class)));
    }

    public static void main(String[] args) {
        test(new Base());

        System.out.println("=========================");
        test(new Derived());
    }
}







/* Output:
Testing x of type class typeinfo.Base
x instanceof Base true
x instanceof Derived false
Base.isInstance(x) true
Derived.isInstance(x) false
x.getClass() == Base.class true
x.getClass() == Derived.class false
x.getClass().equals(Base.class)) true
x.getClass().equals(Derived.class)) false
Testing x of type class typeinfo.Derived
x instanceof Base true
x instanceof Derived true
Base.isInstance(x) true
Derived.isInstance(x) true
x.getClass() == Base.class false
x.getClass() == Derived.class true
x.getClass().equals(Base.class)) false
x.getClass().equals(Derived.class)) true
*///:~
