//: exceptions/E16_CADSystem.java
/****************** Exercise 16 *****************
 * Modify reusing/CADSystem.java to demonstrate
 * that returning from the middle of a try-finally
 * will still perform proper cleanup.
 ***********************************************/
package exceptions;


/*****
 *
 *
 *
 * 268页
 *  修改reusring/CADSystem.java，以演示从try-finally的中间返回仍旧会执行正确的清理
 *
 *
 *
 *
 *
 */
public class E16_CADSystem {
    public static void main(String[] args) {
        reusing.CADSystem x = new reusing.CADSystem(47);
        try {
            return;
        } finally {
            x.dispose();
        }
    }
}