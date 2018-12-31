//: exceptions/E04_ExceptionClass.java
/****************** Exercise 4 ******************
 * Create your own exception class using the
 * extends keyword. Write a constructor for this
 * class that takes a String argument and stores
 * it inside the object with a String reference.
 * Write a method that prints out the stored
 * String. Create a try-catch clause to exercise
 * your new exception.
 ***********************************************/
package exceptions;


/*******
 *
 *
 * 253
 *      使用extends关键字建立一个定义异常类，为这个类写一个的接受字符串的参数的
 * 构造器，把此参数保存在对象内部的字符串的引用中，写一个方法显示此字符串，写一个try-catch
 * 子句，对这个新异常进行测试
 *
 *
 * 1
 *
 */
public class E04_ExceptionClass {
    public static void main(String args[]) {
        try {
            throw new MyException("MyException message");
        } catch (MyException e) {
            e.getMessage();
        }
        try {
            throw new MyException2("MyException2 message");
        } catch (MyException2 e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }
}