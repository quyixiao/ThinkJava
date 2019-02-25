package concurrency;//: concurrency/NaiveExceptionHandling.java
// {ThrowsException}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/****
 *
 * 673 页
 *
 *
 *
 *
 *
 * 将main()主体放到try-catch语句块中是没有作用的
 *
 * 这将产生前面的示例相同的结果，未捕获的异常
 *
 * 为了解决这个问题，我们要修改Executor产生的线程方式，Thread.UncaughtException-Handler 是java SET中的新的接口
 * ，它允许你在每个Thread对象上都附着上都一个异常处理器，Thread.UncaughtExceptionHandler.uncaughtExceiotion
 * 会在线程因未捕获的异常而临近死亡时，被调用，为了使用它，我们创建了一个新类型的ThreadFactory,它将在每个新创建的Thread
 * 对象上附着一个Thread.uncaughtExceptionHandler,我们将这个工厂传递给Executors创建新的ExecutorService的方法。
 *
 *
 * 1
 *
 *
 *
 */
public class NaiveExceptionHandling {
    public static void main(String[] args) {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        } catch (RuntimeException ue) {
            // This statement will NOT execute!
            System.out.println("Exception has been handled!");
        }
    }
} ///:~
