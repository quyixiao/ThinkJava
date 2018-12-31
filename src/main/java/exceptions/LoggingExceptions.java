package exceptions;//: exceptions/LoggingExceptions.java
// An exception that reports through a Logger.

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;


/***、
 *
 * 还可以更进一步定义异常，比如加入额外的构造器和成员
 *
 *
 * 1
 *
 *
 *    异常也是对象的一种，所以可以继续修改这个异常类，以得到更强的功能，但要记住
 *  使用程序包的客户端程序员可能仅仅只是查看一下抛出的异常类型，其他的就不管了，大多数
 *  java库里的异常都是这么用的，所以对异常所添加的其他的功能也许根本用不上
 *
 */
class LoggingException extends Exception {
  private static Logger logger = Logger.getLogger("LoggingException");
  public LoggingException() {
    StringWriter trace = new StringWriter();
    printStackTrace(new PrintWriter(trace));
    logger.severe(trace.toString());
  }
}

public class LoggingExceptions {
  public static void main(String[] args) {
    try {
      throw new LoggingException();
    } catch(LoggingException e) {
      System.err.println("Caught " + e);
    }
    try {
      throw new LoggingException();
    } catch(LoggingException e) {
      System.err.println("Caught " + e);
    }
  }
}


/* Output: (85% match)
Aug 30, 2005 4:02:31 PM LoggingException <init>
SEVERE: LoggingException
        at LoggingExceptions.main(LoggingExceptions.java:19)

Caught LoggingException
Aug 30, 2005 4:02:31 PM LoggingException <init>
SEVERE: LoggingException
        at LoggingExceptions.main(LoggingExceptions.java:24)

Caught LoggingException
*///:~
