//: innerclasses/E06_ProtectedInnerClass.java
/****************** Exercise 6 *****************
 * Create an interface with at least one method,
 * in its own package. Create a class in a
 152 Thinking in Java, 4th Edition Annotated Solution Guide
 * separate package. Add a protected inner class
 * that implements the interface. In a third
 * package, inherit from your class and, inside a
 * method, return an object of the protected
 * inner class, upcasting to the interface during
 * the return.
 ***********************************************/
package innerclasses;

import innerclasses.exercise6.SimpleInterface;
import innerclasses.exercise6b.SimpleClass;


/******
 *
 *
 * 195页
 *  在第一个包中创建一个至少有一个方法的接口，然后在第二包内创建一个类在其中增加一个
 *  protected的内部类以实现那个接口，在第三个包中，继承这个类，并在一个方法中返回该protected内部类的对象，
 *
 *
 * 1
 *
 *一个定义在方法中的类
 *
 */
public class E06_ProtectedInnerClass extends SimpleClass {
    public SimpleInterface get() {
        return new Inner();
    }

    public static void main(String args[]) {
        new E06_ProtectedInnerClass().get().f();
    }
}



