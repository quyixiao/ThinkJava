
//: initialization/E15_StringInstanceInitialization.java
/****************** Exercise 15 ****************
 * Create a class with a String that is
 * initialized using "instance initialization."
 ***********************************************/
package initialization;


/***
 *  98页
 *  编写一个含有字符串域的类，并采用实例初始化方式进行初始化
 *
 */
public class E15_StringInstanceInitialization {


    String s;

    {
        s = "'instance initialization'";
    }

    public E15_StringInstanceInitialization() {
        System.out.println("Default constructor; s = " + s);
    }

    public E15_StringInstanceInitialization(int i) {
        System.out.println("int constructor; s = " + s);
    }

    public static void main(String args[]) {
        new E15_StringInstanceInitialization();
        new E15_StringInstanceInitialization(1);
    }
}