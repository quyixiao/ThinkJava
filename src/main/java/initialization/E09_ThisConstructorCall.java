//: initialization/E09_ThisConstructorCall.java
/****************** Exercise 9 *****************
 * Create a class with two (overloaded)
 * constructors. Using this, call the second
 * constructor inside the first one.
 ***********************************************/
package initialization;


/*****
 *  86页
 *  编写具有两个重载构造器的类，并在第一个构造器中通过this调用第二构造器
 *
 *
 * 1
 */
public class E09_ThisConstructorCall {
    public E09_ThisConstructorCall(String s) {
        System.out.println("s = " + s);
    }

    public E09_ThisConstructorCall(int i) {
        this("i = " + i);
    }

    public static void main(String args[]) {
        new E09_ThisConstructorCall("String call");
        new E09_ThisConstructorCall(47);
    }
}