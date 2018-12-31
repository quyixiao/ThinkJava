//: exceptions/E05_Resumption.java
/****************** Exercise 5 ******************
 * Create your own resumption-like behavior using
 * a while loop that repeats until an exception
 * is no longer thrown.
 ***********************************************/
package exceptions;


/******
 *
 *
 *
 *
 * 253页
 *  使用while的循环建立类似的恢复模型，的异常处理行为，它将不断的重复，直到异常不再抛出
 *
 *  1
 *
 */
class ResumerException extends Exception {
}

class Resumer {
    static int count = 3;

    static void f() throws ResumerException {
        if (--count > 0)
            throw new ResumerException();
    }
}

public class E05_Resumption {
    public static void main(String args[]) {
        while (true) {
            try {
                Resumer.f();
            } catch (ResumerException e) {
                System.out.println("Caught " + e);
                continue;
            }
            System.out.println("Got through...");
            break;
        }
        System.out.println("Successful execution");
    }
}