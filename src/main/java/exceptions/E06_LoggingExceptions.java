//: exceptions/E06_LoggingExceptions.java
/****************** Exercise 6 ******************
 * Create two exception classes, each of which
 * performs its own logging automatically.
 * Demonstrate that these work.
 ***********************************************/
package exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;


/*******
 *
 *
 * 255页
 *      创建两个异常类，每一个都自动记录它们自己的日志，演示它们都可以正常运行
 *
 *
 * 1
 */
class LoggingException1 extends Exception {
    private static Logger logger =
            Logger.getLogger("LoggingException1");

    public LoggingException1() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }
}

class LoggingException2 extends Exception {
    private static Logger logger =
            Logger.getLogger("LoggingException2");

    public LoggingException2() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }
}

public class E06_LoggingExceptions {
    public static void main(String[] args) {
        try {
            throw new LoggingException1();
        } catch (LoggingException1 e) {
            System.err.println("Caught " + e);
        }
        try {
            throw new LoggingException2();
        } catch (LoggingException2 e) {
            System.err.println("Caught " + e);
        }
    }
}