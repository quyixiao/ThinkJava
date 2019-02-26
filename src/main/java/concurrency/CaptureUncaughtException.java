package concurrency;//: concurrency/CaptureUncaughtException.java

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


/****
 * 674  页
 *
 * 在程序中添加了额外的跟踪机制，用来验证工厂创建的线程会传递给UncaughtException
 *  上面的示例使得你可以按照具体情况逐个地设置处理器，如果你知道将要在代码中处处使用
 *  相同的异常处理器，那么简单的方式是在Thread类中设置一个静态域，并将这个处理器设置为
 *  默认的未捕获的异常处理器。
 *
 *
 * 这个处理器只有在不存在的线程专有的未捕获异常处理器的情况下才会被调用。系统会检查线程专有的版本
 * ，如果没有发现，则检查线程组是否有其他的uncatchExcetion()方法，如果也没有，再调用defaultUncatchExctionHander
 *
 * 可以把单线程序当作在问题域求解的单一实体，每次只能做一件事情，因为只有一个实体，所以永远不用担心诸如， 两个实体试图
 * 同时使用一个资源，这样的问题一比如，两个人在同一个地方停车，两个人同时走过一扇门，甚至是两个人同时说话。
 * 有了并发就可以同时做多件事情了，但是，两个或多个线程彼此互相干涉的问题也就应该，如果不防范这种冲突，就可能发生
 * 两个线程同时试图访问同一个银行账户，或向同一个打印改变同一个值等诸如引类的问题
 *
 *
 * 现在可以看到，未捕获的异常是通过uncaughtException来捕获的
 * 上面的示例使得你可以按具体情况逐个，再调用defaultUncaughtExceptionHandler
 *
 *
 * 如果你正在写一个变量，它可能接下来将被另一个线程读取，或者正在读取一个上一次已经
 * 被另一个线程写过的变量，那么你必须使用同步，并且，读写线程都必须相同的的监视器锁同步
 *
 *
 *
 *
 *
 *
 *
 * 1
 *
 *
 *
 */
class ExceptionThread2 implements Runnable {
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t );
        System.out.println(
                "eh = " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

class MyUncaughtExceptionHandler implements
        Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

class HandlerThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        Thread t = new Thread(r);
        System.out.println("created " + t);
        t.setUncaughtExceptionHandler(
                new MyUncaughtExceptionHandler());
        System.out.println(
                "eh = " + t.getUncaughtExceptionHandler());
        return t;
    }
}

public class CaptureUncaughtException {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExceptionThread2());
    }
}




/* Output: (90% match)
HandlerThreadFactory@de6ced creating new Thread
created Thread[Thread-0,5,main]
eh = MyUncaughtExceptionHandler@1fb8ee3
run() by Thread[Thread-0,5,main]
eh = MyUncaughtExceptionHandler@1fb8ee3
caught java.lang.RuntimeException
*///:~
