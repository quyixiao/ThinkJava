package innerclasses;//: innerclasses/MultiNestingAccess.java
// Nested classes can access all members of all
// levels of the classes they are nested within.


/****
 * 一个内部类被嵌套多少层并不重要，它能透明的访问所有的它所嵌入的外围类的所有成员，如下
 *
 *
 *      可以看到在MNA.A.B中，调用方法g()和f()不需要任何条件，即使它们被定义为private
 *  这个例子同时展示了如何从不同的类里创建多层内部类的对象的基本语法，".NEW"语法能
 *  产生正确的作用域，所以不必在调用构造器时限定类名
 *
 *
 *  每个内部类都能独立地继承自一个接口的实现，所以无论外围类是否己经继承了某个接口的实现，对于内部类都没有影响
 *
 *
 *
 *  如果没有内部类提供的，可以继承多个具体或抽象的类的能力，一些设计与编程问题就很难解决，
 *  从这个角度看，内部类使得多重继承的解决应该要变得完整，接口解决了部分问题
 *  而内部类有效的实现我多重继承，也就是说，内部类允许继承多个非接口类型，类或抽象类
 *
 *
 */
class MNA {
    private void f() {
    }

    class A {
        private void g() {
        }

        public class B {
            void h() {
                g();
                f();
            }
        }
    }
}

public class MultiNestingAccess {
    public static void main(String[] args) {
        MNA mna = new MNA();
        MNA.A mnaa = mna.new A();
        MNA.A.B mnaab = mnaa.new B();
        mnaab.h();
    }
} ///:~
