//: exceptions/E19_GuardedLostMessage.java
/****************** Exercise 19 *****************
 * Repair the problem in LostMessage.java by
 * guarding the call in the finally clause.
 ***********************************************/
package exceptions;


/******
 *
 *
 *
 * 269页
 *  通过确保finally子句中的调用，来修复LostMessage.java中的问题
 *
 */
public class E19_GuardedLostMessage {
    void f() throws VeryImportantException {
        throw new VeryImportantException();
    }

    void dispose() throws HoHumException {
        throw new HoHumException();
    }

    public static void main(String[] args) {
        try {
            LostMessage lm = new LostMessage();
            try {
                lm.f();
            } finally {
                try {
                    lm.dispose();
                } catch (HoHumException e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}