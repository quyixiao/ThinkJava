//: innerclasses/E26_InnerClassInheritance.java
/****************** Exercise 26 *****************
 Inner Classes 175
 * Create a class with an inner class that has a
 * non-default constructor (one that takes
 * arguments). Create a second class with an inner
 * class that inherits from the first inner class.
 ************************************************/
package innerclasses;

/*****
 *
 * 212页
 *
 * 创建一个包含内部类的类，此内部类有一个非默认的构造器（需要参加的构造器）
 * 创建另一个也包含内部类的类，此内部类继承自第一个内部类
 *
 */
class WithNonDefault {
    class Inner {
        int i;

        public Inner(int i) {
            this.i = i;
        }

        public Inner() {
            i = 47;
        }

        public void f() {
            System.out.println("Inner.f");
        }
    }
}

public class E26_InnerClassInheritance {
    class Inner2 extends WithNonDefault.Inner {
        // Won't compile -- WithNonDefault not available:
        //! public Inner2(int i) {
        //!   super(i);
        //! }
        public Inner2(WithNonDefault wnd, int i) {
            wnd.super(i);
        }

        public void f() {
            System.out.println("Inner2.f");
            super.f();
        }
    }

    public static void main(String args[]) {
        WithNonDefault wnd = new WithNonDefault();
        E26_InnerClassInheritance ici =
                new E26_InnerClassInheritance();
        Inner2 i2 = ici.new Inner2(wnd, 47);
        i2.f();
    }
}