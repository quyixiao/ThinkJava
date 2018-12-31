//: interfaces/E02_Abstract.java
// {CompileTimeError}
/****************** Exercise 2 *****************
 * Create a class as abstract without including
 * any abstract methods, and verify that you
 * cannot create any instances of that class.
 ***********************************************/
package interfaces;

/****
 *
 *  171页
 *
 *  创建一个不包含任何抽象方法的抽象类，并验证我们不能为该类创建任何实例
 *
 */
abstract class NoAbstractMethods {
    void f() {
        System.out.println("f()");
    }
}

public class E02_Abstract {

    public static void main(String args[]) {
        /*new NoAbstractMethods();*/
    }
} 