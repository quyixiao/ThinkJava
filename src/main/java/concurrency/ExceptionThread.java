package concurrency;//: concurrency/ExceptionThread.java
// {ThrowsException}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/****
 *
 * 672页
 *
 * 由于线程的本质特性，使得你不能捕获你从线程逃逸出来的异常，一旦异常逃出任务的run()方法，它就会向外传播到
 * 控制台，除非你采取特殊的步骤捕获这种错误的异常，在java sET5之前，你可以使用线程组来捕获这些异常，但是有了
 * java set 5 ,就可以用Executor来解决这个问题，因此你就不再需要了解有关的线程组的知识了，除非要理解遗留代码
 * ，请查看可能从www.mindView.net下载的， 以了解线程组的细节
 *
 * 下面的任务总是抛出一个异常，该异常会传播到其他的run()方法的外部，并且 main() 展示了当你运行它时发生的事情
 *
 *
 */
public class ExceptionThread implements Runnable {
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
    }
} ///:~
