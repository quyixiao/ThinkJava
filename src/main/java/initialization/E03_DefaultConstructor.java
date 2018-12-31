
//: initialization/E03_DefaultConstructor.java
/****************** Exercise 3 ******************
 * Create a class with a default constructor (one
 * that takes no arguments) that prints a
 * message. Create an object of this class.
 ************************************************/
package initialization;


/*****
 *
 *83页
 *
 *
 * 创建一个带默认构造器（即元参构造器）的类，在构造器中打印一条消息为
 * 这个类创建一个对象
 *
 *
 * 1
 */
public class E03_DefaultConstructor {
    E03_DefaultConstructor() {
        System.out.println("Default constructor");
    }

    public static void main(String args[]) {
        new E03_DefaultConstructor();
    }
}