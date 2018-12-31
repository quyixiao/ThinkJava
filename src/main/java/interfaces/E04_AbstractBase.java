//: interfaces/E04_AbstractBase.java
/****************** Exercise 4 *****************
 * Create an abstract class with no methods.
 * Derive a class and add a method. Create a
 * static method that downcasts a reference from
 * the base class to the derived class and calls
 * the method. Demonstrate that it works in main().
 * Eliminate the need for the downcast by moving
 * the abstract declaration to the base class.
 ***********************************************/
package interfaces;


/*****
 *
 * 172页
 *  创建一个不包含任何方法的抽象类，从它那里导出一个类，并添加一个方法创建一个静态方法，
 *  它可以接受指向基类的引用，将其向下转型到导出类，然后再调用该静态方法，在main()中
 *  展现它的运行情况，然后，为基类中的方法加上abstract声明，这样就不再需要进行向下转型了
 *
 */
abstract class NoMethods {
}

class Extended1 extends NoMethods {
    public void f() {
        System.out.println("Extended1.f");
    }
}

abstract class WithMethods {
    abstract public void f();
}

class Extended2 extends WithMethods {
    public void f() {
        System.out.println("Extended2.f");
    }
}

public class E04_AbstractBase {
    public static void test1(NoMethods nm) {
        // Must downcast to access f():
        ((Extended1) nm).f();
    }

    public static void test2(WithMethods wm) {
        // No downcast necessary:
        wm.f();
    }

    public static void main(String args[]) {
        NoMethods nm = new Extended1();
        test1(nm);
        WithMethods wm = new Extended2();
        test2(wm);
    }
}