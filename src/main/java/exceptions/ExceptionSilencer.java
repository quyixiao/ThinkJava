package exceptions;//: exceptions/ExceptionSilencer.java


/****
 *  269页
 *
 *
 *
 * 这个程序即使抛出异常，也不会有任何输出。
 *
 *
 *   1
 *
 */
public class ExceptionSilencer {
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } finally {
            // Using 'return' inside the finally block
            // will silence any thrown exception.
            return;
        }
    }
} ///:~
