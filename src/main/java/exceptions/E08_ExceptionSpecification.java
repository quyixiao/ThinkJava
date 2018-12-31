//: exceptions/E08_ExceptionSpecification.java
/****************** Exercise 8 ******************
 * Write a class with a method that throws an
 * exception of the type created in Exercise 4.
 * Try compiling it without an exception
 * specification to see what the compiler says.
 * Add the appropriate exception specification.
 * Try out your class and its exception inside a
 * try-catch clause.
 ***********************************************/
package exceptions;

/*******
 *
 *
 *
 * 256页
 *      定义一个类，令其方法抛出在练习2里定义的异常，不用异常说明，看看能否通过
 *  编译，然后加上异常说明，用try-catch子句测试该类和异常
 *
 *
 *  1
 *
 *
 *
 *
 *
 */
class Thrower {
    public void f() {
        // Compiler gives an error: "unreported
        // exception MyException; must be caught
        // or declared to be thrown"
        //! throw new MyException("Inside f()");
    }

    public void g() throws MyException {
        throw new MyException("Inside g()");
    }
}

public class E08_ExceptionSpecification {
    public static void main(String args[]) {
        Thrower t = new Thrower();
        try {
            t.g();
        } catch (MyException e) {

            System.out.println(e.getMessage());
            // e.printMsg();
        }
    }
}