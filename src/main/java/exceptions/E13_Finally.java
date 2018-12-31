//: exceptions/E13_Finally.java
// {ThrowsException}
/****************** Exercise 13 *****************
 * Modify Exercise 9 by adding a finally clause.
 * Verify that your finally clause is executed, even
 * if a NullPointerException is thrown.
 ***********************************************/
package exceptions;


import java.text.SimpleDateFormat;
import java.util.Date;

/*******
 *
 *
 * 267页
 * 修改练习9，加一个finally子句，验证一下，即便是抛出NullPointerExcetion异常，
 * finally子句也会得到执行
 *
 *
 *
 * 1
 *
 */
public class E13_Finally {
    public static void throwNull() {
        throw new NullPointerException();
    }

    public static void main(String args[]) {
        SimpleDateFormat aDate = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss.SSS");

        Thrower2 t = new Thrower2();
        try {
            t.f();
        } catch (ExBase e) {
            System.err.println(aDate.format(new Date()) + "\tcaught +++++++++" + e);
        } finally {
            System.out.println(aDate.format(new Date()) + "\tIn finally clause A");
        }
        try {
            throwNull();
            t.f();
        } catch (ExBase e) {
            System.err.println(aDate.format(new Date()) + "\t=========caught " + e);
        } finally {
            System.out.println(aDate.format(new Date()) + "\tIn finally clause B");
        }
    }
} 