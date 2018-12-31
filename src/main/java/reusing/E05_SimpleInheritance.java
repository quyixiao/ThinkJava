//: reusing/E05_SimpleInheritance.java
/****************** Exercise 5 *****************
 * Create classes A and B with default
 * constructors (empty argument lists) that
 * announce themselves. Inherit a new class
 * called C from A, and create a member of class
 * B inside C. Do not create a constructor for C.
 * Create an object of class C and observe the
 * results.
 ***********************************************/
package reusing;

class A {
    public A() {
        System.out.println("A()");
    }
}

class B {
    public B() {
        System.out.println("B()");
    }
}

class C extends A {
    B b = new B();
}


/***
 * 130页
 * 创建两个带有默认构造器（空参数列表）的类Q和类B,从A中继承产生一个名为C的亲类，并在
 * C内创建一个B类的成员，不要给C编写构造器，创建一个C类的对象并观察其结果。
 *
 */
public class E05_SimpleInheritance {
    public static void main(String args[]) {
        new C();
    }
}