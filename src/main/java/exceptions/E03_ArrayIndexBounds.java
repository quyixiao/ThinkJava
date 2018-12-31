package exceptions;


/*****
 *
 *
 *
 *
 * 253页
 *  编写能产生并能捕获ArrayIndexOutOfBoundsExcetion异常的代码
 *
 *
 * 1
 */
public class E03_ArrayIndexBounds {
    public static void main(String args[]) {
        char[] array = new char[10];
        try {
            array[10] = 'x';
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("e = " + e);
        }
    }
}