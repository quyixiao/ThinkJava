package initialization;


//: initialization/E02_StringInitialization.java
/****************** Exercise 2 *****************
 * Create a class with a String field initialized
 * at the point of definition, and another one
 * initialized by the constructor. What is the
 * difference between the two approaches?
 ***********************************************/

/****
 *  77页
 *  创建了一个类，它包含了一个在定义时就被初始化了的String域，以及另一个通过构造器
 *  初始化的String域，这两种方式有何差异
 *
 *
 *
 */
public class E02_StringInitialization {
    String s1 = "Initialized at definition";
    String s2;

    public E02_StringInitialization(String s2i) {
        s2 = s2i;
    }

    public static void main(String args[]) {
        E02_StringInitialization si =
                new E02_StringInitialization(
                        "Initialized at construction");
        System.out.println("si.s1 = " + si.s1);
        System.out.println("si.s2 = " + si.s2);
    }
}