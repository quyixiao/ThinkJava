//: innerclasses/E05_InstanceOfInner.java
/****************** Exercise 5 *****************
 * Create a class with an inner class. In a
 * separate class, make an instance of the inner
 * class.
 ***********************************************/
package innerclasses;


/*****
 * 194页
 * 创建一个包含内部类的类，在另一个独立的类中，创建此内部类的实例

 1
 *
 */
class Outer3 {
    class Inner {
        {
            System.out.println("Inner created");
        }
    }
}

public class E05_InstanceOfInner {
    public static void main(String args[]) {
        Outer3 o = new Outer3();
        Outer3.Inner i = o.new Inner();
    }
}