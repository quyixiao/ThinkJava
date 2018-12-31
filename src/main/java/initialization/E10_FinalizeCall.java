//: initialization/E10_FinalizeCall.java
/****************** Exercise 10 *****************
 * Create a class with a finalize() method that
 * prints a message. In main(), create an object
 * of your class. Explain the behavior of your
 * program.
 ************************************************/
package initialization;


/****
 * 89页
 *  编写具有finalize()方法的类，并在方法中打印消息，在main()中为该类创建一个
 *  对象，试解释这个程序的行为
 *
 *
 * 1
 *
 *
 */
public class E10_FinalizeCall {
    protected void finalize() {
        System.out.println("finalize() called");
    }

    public static void main(String args[]) {
        new E10_FinalizeCall();
    }
} 