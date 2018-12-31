//: initialization/E14_StaticStringInitialization.java
/****************** Exercise 14 *****************
 * Create a class with a static String field that
 * is initialized at the point of definition, and
 * another one initialized by the static
 * block. Add a static method that prints both
 * fields and demonstrates that they are both
 * initialized before they are used.
 ************************************************/
package initialization;


/***
 * 97页
 *      编写一个类，拥有两个不够成熟字符串域，其中一个在定义处初始化，另和个在静态块中初始化，
 *  现在，加入一个静态方法用以打印出一个个字段值，请证明它们都会在被使用之前
 *  完成初始化动作
 *
 *
 * 1
 */
public class E14_StaticStringInitialization {
    static String s1 = "Initialized at definition";
    static String s2;

    static {
        System.out.println("========================");
        s2 = "Initialized in static block";
    }

    public static void main(String args[]) {
        /*System.out.println("s1 = " + s1);
        System.out.println("++++++++++++++++++++++++");
        System.out.println("s2 = " + s2);*/
    }
}