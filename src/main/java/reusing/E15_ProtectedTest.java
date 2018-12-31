//: reusing/protect/E15_Protected.java
/****************** Exercise 15 *****************
 * Create a class with a protected method inside
 * a package. Try to call the protected method
 * outside the package, and explain the results.
 * Now inherit from your class and call the
 * protected method from inside a method of your
 * derived class.
 ***********************************************/
package reusing;

 class E15_Protected {
    protected void f() {
    }
} ///:~
//: reusing/E15_ProtectedTest.java

class Derived extends E15_Protected {
    public void g() {
        f(); // Accessible in derived class
    }
}


/****
 * 138页
 * 在包中编写一个类，类应具有一个protected方法，还应注意Orc的toString()方法
 * 的定义方式，它依据toString()的基类版本而定义
 *
 */
public class E15_ProtectedTest {
    public static void main(String args[]) {

        //! new E15_Protected().f(); // Cannot access
        new Derived().g();
    }
}