package exceptions;


/******
 *
 *
 *
 * 253页
 *  定义一个对象引用并初始化为null，尝试用此引用调用方法，把这个调用放在try-catch子句中
 *  里以捕获异常
 *
 *
 *  1
 *
 *
 */
public class E02_NullReference {
    public static void main(String args[]) {
        String s = null;
        // Causes a NullPointerException:
        //! s.toString();
        try {
            s.toString();
        } catch (Exception e) {
            System.out.println("Caught exception " + e);
        }
    }
}