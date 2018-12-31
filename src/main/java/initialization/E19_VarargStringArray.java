//: initialization/E19_VarargStringArray.java
/****************** Exercise 19 *****************
 * Write a method that takes a vararg String
 * array. Verify that you can pass either a
 * comma-separated list of Strings or a
 * String[] into this method.
 ************************************************/
package initialization;


/**
 *
 * 105页
 *  写一个类，接受一个可变参数的String数组，验证你可以向该方法传递一个
 *  用逗号分隔的String列表，或是一个String[]
 *
 */
public class E19_VarargStringArray {

    static void printStrings(String... strs) {
        for (String s : strs)
            System.out.println(s);
    }

    public static void main(String args[]) {
        printStrings("These", "are", "some", "strings");
        printStrings(
                new String[]{"These", "are", "some", "strings"}
        );
    }
}