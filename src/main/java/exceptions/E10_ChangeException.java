//: exceptions/E10_ChangeException.java
/****************** Exercise 10 ******************
 * Create a class with two methods, f() and g().
 * In g(), throw an exception of a new type that
 * you define. In f(), call g(), catch its
 * exception and, in the catch clause, throw a
 * different exception (of a second type that you
 * define). Test your code in main().
 ***********************************************/
package exceptions;


/******
 * 263页
 * 为一个类定义两个方法，f()和g()，在g()里，抛出一个的自定义的新异常，在f()里
 * 调用g()，捕获它抛出的异常，并且在catch子句里抛出另一个异常，自定义的第二种异常
 * 在main()里测试代码
 *
 *
 */
class AnException extends Exception {
}

class AnotherException extends Exception {
}

public class E10_ChangeException {
    public void g() throws AnException {
        throw new AnException();
    }

    public void f() throws AnotherException {
        try {
            g();
        } catch (AnException e) {
            throw new AnotherException();
        }
    }

    public static void main(String args[]) {
        E10_ChangeException ce = new E10_ChangeException();
        try {
            ce.f();
        } catch (AnotherException e) {
            System.out.println("Caught " + e);
        }
    }
}