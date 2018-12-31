// {ThrowsException}
/****************** Exercise 11 ******************
 * Repeat the previous exercise, but inside the
 * catch clause, wrap g()'s exception in a
 * RuntimeException.
 ***********************************************/
package exceptions;


/******
 * 263页
 * 重复上一个练习，但是在catch子句里把g()要抛出的异常包装成一个RuntimeException
 *
 */
class AnException2 extends Exception {
}

public class E11_ChangeToRuntimeException {
    public void g() throws AnException2 {
        throw new AnException2();
    }

    public void f() {
        try {
            g();
        } catch (AnException2 e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) {
        E11_ChangeToRuntimeException ce =
                new E11_ChangeToRuntimeException();
        ce.f();
    }
} 