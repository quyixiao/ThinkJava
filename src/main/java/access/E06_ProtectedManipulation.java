//: access/E06_ProtectedManipulation.java
/****************** Exercise 6 *****************
 * Create one class with protected data, and a
 * second class in the same file with a method
 * that manipulates that protected data.
 ***********************************************/
package access;

class WithProtected {
    protected int i;
}


/***
 *
 *
 * 120页
 * 创建一个带有protecded数据的类，运用在第一个类中处理protected数据方法在
 * 相同的文件中创建第二个类
 *
 */
public class E06_ProtectedManipulation {
    public static void main(String args[]) {
        WithProtected wp = new WithProtected();
        wp.i = 47;
        System.out.println("wp.i = " + wp.i);
    }
}