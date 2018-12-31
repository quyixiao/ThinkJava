//: exceptions/E15_WithFinally.java
/****************** Exercise 15 *****************
 * Show that WithFinally.java doesn't fail by
 * throwing a RuntimeException inside the try
 * block.
 ***********************************************/
package exceptions;


/*******
 *
 *
 *
 * 267页
 * 试说明，在WithFinally.java的try内部抛出RuntimeException，程序不会出现错误
 *
 *
 * 1
 *
 *
 *
 *
 */
public class E15_WithFinally {
    static Switch sw = new Switch();

    static void f() throws OnOffException1, OnOffException2 {
        throw new RuntimeException("Inside try");
    }

    public static void main(String[] args) {
        try {
            try {
                sw.on();
                // Code that can throw exceptions...
                f();
            } catch (OnOffException1 e) {
                System.out.println("OnOffException1");
            } catch (OnOffException2 e) {
                System.out.println("OnOffException2");
            } finally {
                sw.off();
            }
        } catch (RuntimeException e) {
            System.out.println("Exception '" + e +
                    "'. Did the switch get turned off?");
            System.out.println(sw);
        }
    }
}