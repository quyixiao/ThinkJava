//: exceptions/E09_CatchAll.java
/****************** Exercise 9 ******************
 * Create three new types of exceptions. Write a
 * class with a method that throws all three. In
 * main(), call the method but only use a single
 * catch clause that will catch all three types
 * of exceptions.
 ***********************************************/
package exceptions;


/********
 *
 *
 *
 * 257页
 *  定义三种新的异常类型，写一个类，在一个方法中抛出这三种异常，在main()
 *  里调用这个方法，仅用一个catch出了名捕获这三种异常
 *
 */
class ExBase extends Exception {
}

class Ex1 extends ExBase {
}

class Ex2 extends ExBase {
}

class Ex3 extends ExBase {
}

class Thrower2 {
    void f() throws Ex1, Ex2, Ex3 {
        throw new Ex2();
        // You aren't forced to throw all the
        // exceptions in the specification.
    }
}

public class E09_CatchAll {
    public static void main(String args[]) {
        Thrower2 t = new Thrower2();
        try {
            t.f();
        } catch (ExBase e) {
            System.out.println("caught " + e);
        } catch (Exception e) {
            System.out.println("caught " + e);
        }
    }
}