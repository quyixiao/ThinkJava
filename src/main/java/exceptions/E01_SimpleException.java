//: exceptions/E01_SimpleException.java
/****************** Exercise 1 ******************
 * Create a class with a main() that throws an
 * object of class Exception inside a try block.
 * Give the constructor for Exception a String
 * argument. Catch the exception inside a catch
 * clause and print the String argument. Add a
 * finally clause and print a message to prove
 * you were there.
 ***********************************************/
package exceptions;


/*****8
 *
 *
 *
 * 253页
 *  编写一个类，在其main()方法的try块里抛出一个Exception类的对象，传递一个
 *  字符串参数给Exception的构造器，在catch子句中捕获此异常对象，并且打印字符串参数，添加
 *  一个finally出了名，打印一条信息以证明这里确实得到了执行
 *
 *
 * 1
 */
public class E01_SimpleException {
    public static void main(String args[]) {
        try {
            throw new Exception("An exception in main");
        } catch (Exception e) {
            System.out.println(
                    "e.getMessage() = " + e.getMessage());
        } finally {
            System.out.println("In finally clause");
        }
    }
}