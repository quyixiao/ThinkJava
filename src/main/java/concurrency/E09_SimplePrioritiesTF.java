//: concurrency/E09_SimplePrioritiesTF.java
/****************** Exercise 9 *****************
 * Modify SimplePriorities.java so that a custom
 * ThreadFactory sets the priorities of the threads.
 ***********************************************/
package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


/***
 * 线程的的优先级将应
 *
 *
 *
 *           Java线程中的Thread.yield( )方法，译为线程让步。顾名思义，就是说当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，

 让自己或者其它的线程运行，注意是让自己或者其他线程运行，并不是单纯的让给其他线程。

 yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；但是，并不能保

 证在当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行！

 举个例子：一帮朋友在排队上公交车，轮到Yield的时候，他突然说：我不想先上去了，咱们大家来竞赛上公交车。然后所有人就一块冲向公交车，

 有可能是其他人先上车了，也有可能是Yield先上车了。

 但是线程是有优先级的，优先级越高的人，就一定能第一个上车吗？这是不一定的，优先级高的人仅仅只是第一个上车的概率大了一点而已，

 最终第一个上车的，也有可能是优先级最低的人。并且所谓的优先级执行，是在大量执行次数中才能体现出来的。


 */
class SimplePriorities2 implements Runnable {
    private int countDown = 5;
    private volatile double d; // No optimization

    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    public void run() {
        for (; ; ) {
            // An expensive, interruptable operation:
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }
}

class PriorityThreadFactory implements ThreadFactory {
    private final int priority;

    PriorityThreadFactory(int priority) {
        this.priority = priority;
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setPriority(priority);
        return t;
    }
}

public class E09_SimplePrioritiesTF {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(
                new PriorityThreadFactory(Thread.MIN_PRIORITY));
        for (int i = 0; i < 5; i++)
            exec.execute(new SimplePriorities2());
        Thread.yield();
        exec.shutdown();
        exec = Executors.newCachedThreadPool(new PriorityThreadFactory(Thread.MAX_PRIORITY));

        exec.execute(new SimplePriorities2());
        Thread.yield();
        exec.shutdown();
    }
}