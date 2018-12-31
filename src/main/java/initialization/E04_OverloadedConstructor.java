
//: initialization/E04_OverloadedConstructor.java
/****************** Exercise 4 *****************
 * Add an overloaded constructor to Exercise 3 that
 * takes a String argument and prints it along with
 * your message.
 ***********************************************/
package initialization;


/****
 *
 *
 * 83页
 * 为前一个练习中的类添加一个重载构造器，令其接收一个字符串参数，并在构造器中把
 * 你自己的消息和接收的参数一起打印出来
 *
 *
 * 1
 */
public class E04_OverloadedConstructor {
    E04_OverloadedConstructor() {
        System.out.println("Default constructor");
    }

    E04_OverloadedConstructor(String s) {
        System.out.println("String arg constructor");
        System.out.println(s);
    }

    public static void main(String args[]) {
        // Call default version:
        new E04_OverloadedConstructor();
        // Call overloaded version:
        new E04_OverloadedConstructor("Overloaded");
    }
}