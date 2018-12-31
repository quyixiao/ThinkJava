
//: exceptions/E27_RuntimeArrayIndexBounds.java
// {ThrowsException}
/****************** Exercise 27 ******************
 * Modify Exercise 3 to convert the exception to a
 * RuntimeException.
 ***********************************************/
package exceptions;


/******
 * 281页
 * 修改练习3 ，将异常转变为RunTimeException
 *
 *
 *
 * 1
 */
public class E27_RuntimeArrayIndexBounds {
    public static void main(String args[]) {
        char[] array = new char[10];
        try {
            array[10] = 'x';
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
    }
}