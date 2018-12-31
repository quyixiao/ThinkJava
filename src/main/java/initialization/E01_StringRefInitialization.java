package initialization;

//: initialization/E01_StringRefInitialization.java
/****************** Exercise 01 ****************
 * Create a class with an uninitialized
 * String reference. Demonstrate that this
 * reference is initialized by Java to null.
 ***********************************************/

/****
 *
 * 77页
 * 创建一个类，它包含一个未初始化的String引用，验证该引用被Java初始化成了null
 *
 */
public class E01_StringRefInitialization {
    String s;

    public static void main(String args[]) {
        E01_StringRefInitialization sri =
                new E01_StringRefInitialization();
        System.out.println("sri.s = " + sri.s);
    }
}