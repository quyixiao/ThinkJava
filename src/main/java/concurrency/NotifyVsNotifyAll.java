package concurrency;//: concurrency/NotifyVsNotifyAll.java

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/****
 *     708页
 *
 *     因为在技术上，可能会有多个任务在单个Car对象上处于wait()状态，因此调用notifyAll()比
 *  只调用notify()要更安全，但是，上面程序的结构只会有一个任务实际处于wait()状态，因此你可
 *  以使用notify()来代替nofityAll()
 *      使用nofity()而不是notifyAll()是一种优化，使用nofify()时，使用notify()时，在人多等待同一个锁的任务中
 *  只有一个会被唤醒，因此如果你希望使用notify()，就必须保证被唤醒的是恰当的任务，另外
 *  为了使用nofity()所有任务必须等待相同的条件，因为如果你有多个任务在等待不同的条件，
 *  那么你就不会知道是否唤醒了恰当的任务，如果使用notify()，当条件发生变化时，必须只有一个
 *  任务能够从中受益，最后，这些限制对所有的可能存在的子类都必须总是起作用的，如果这些
 *  规则中有任何一条不满足，那么你就必须 使用nofityAll()而不是notify()
 *      在有的java线程机制的讨论中，有一个令人困惑的描述，notifyAll()将唤醒，所有正在等
 *  待的任务，这是否意味着在程序中任务地方，任何处于wait()wait()状态的任务都将被任务对
 *  nofityAll()的调用唤醒呢，在下面的示例中，与Task2相关的代码说明了情况并非如此，事实上
 *  当nofityAll()因某个特定的锁而被调用时，只有等待这个锁的任务才会被唤醒
 *      Task和Task2每个都有其自己的Blocker对象，因此每个Task对象都会在Task.blocker上阻塞
 *  而每个Task2都会在Task2.blocker上阻塞，在main()中，java.util.Timer对象设置为每个4/10秒执
 *  行一次run()方法，而这个run()方法将经由 激励方法交替地在Task.blocker上调用notify()和
 *  notifyAll()
 *      从输出中你可以看到。即使存在Task2.blocker上阻塞的Task2对象，也没有任何在Task.blocker
 * 上的notify或notifyAll()调用会导致Task2对象被唤醒，与此类似，在main方法的结尾
 * 调用了timer的cancel()，即使计时器被撤销了，前5个任务也依然运行，并仍旧在它们对
 * Task.blocker.waitingCall()的调用中被阻塞，对Task2.blocker.prodAll()的调用产生的输出不包括
 * 任何在Task.blocker中的锁上的等待的任务。
 *      如果你浏览的Blocker中的prod()和prodAll()，就会发现这是有意义的，这些方法是
 *  synchronized的，这意味着它们将获取自身的锁，因此它们调用notify()或notifyAll()时，只在
 *  这个锁上的调用是行使逻辑的，因此，将只唤醒在等待这个锁的任务。
 *      Blocker.waitingCall()非常简单，因为在本例中，你只需声明for(;;)而不是while(!Thread.interrupted)
 * 就可以达到相同的效果，因为存在着两种离开循环的方式，但是，事实上，这个示例选择了检查interrupted()，因为
 * 存在着两种离开循环的方式，如果在以后的某个
 * 时刻，你决定要在循环中添加更多代码，那么如果没有覆盖从这个循环中退出的这两条路径，
 * 就会产生引入错误的风险。
 *
 *
 *
 */
class Blocker {
    synchronized void waitingCall() {
        try {
            while (!Thread.interrupted()) {
                wait();
                System.out.print(Thread.currentThread() + " ");
            }
        } catch (InterruptedException e) {
            // OK to exit this way
        }
    }

    synchronized void prod() {
        notify();
    }

    synchronized void prodAll() {
        notifyAll();
    }
}

class Task implements Runnable {
    static Blocker blocker = new Blocker();

    public void run() {
        blocker.waitingCall();
    }
}

class Task2 implements Runnable {
    // A separate Blocker object:
    static Blocker blocker = new Blocker();

    public void run() {
        blocker.waitingCall();
    }
}

public class NotifyVsNotifyAll {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Task());
        exec.execute(new Task2());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prod = true;

            public void run() {
                if (prod) {
                    System.out.print("\nnotify() ");
                    Task.blocker.prod();
                    prod = false;
                } else {
                    System.out.print("\nnotifyAll() ");
                    Task.blocker.prodAll();
                    prod = true;
                }
            }
        }, 400, 400); // Run every .4 second
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        timer.cancel();
        System.out.println("\nTimer canceled");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.print("Task2.blocker.prodAll() ");
        Task2.blocker.prodAll();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nShutting down");
        exec.shutdownNow(); // Interrupt all tasks
    }
}




/* Output: (Sample)
notify() Thread[pool-1-thread-1,5,main]
notifyAll() Thread[pool-1-thread-1,5,main] Thread[pool-1-thread-5,5,main] Thread[pool-1-thread-4,5,main] Thread[pool-1-thread-3,5,main] Thread[pool-1-thread-2,5,main]
notify() Thread[pool-1-thread-1,5,main]
notifyAll() Thread[pool-1-thread-1,5,main] Thread[pool-1-thread-2,5,main] Thread[pool-1-thread-3,5,main] Thread[pool-1-thread-4,5,main] Thread[pool-1-thread-5,5,main]
notify() Thread[pool-1-thread-1,5,main]
notifyAll() Thread[pool-1-thread-1,5,main] Thread[pool-1-thread-5,5,main] Thread[pool-1-thread-4,5,main] Thread[pool-1-thread-3,5,main] Thread[pool-1-thread-2,5,main]
notify() Thread[pool-1-thread-1,5,main]
notifyAll() Thread[pool-1-thread-1,5,main] Thread[pool-1-thread-2,5,main] Thread[pool-1-thread-3,5,main] Thread[pool-1-thread-4,5,main] Thread[pool-1-thread-5,5,main]
notify() Thread[pool-1-thread-1,5,main]
notifyAll() Thread[pool-1-thread-1,5,main] Thread[pool-1-thread-5,5,main] Thread[pool-1-thread-4,5,main] Thread[pool-1-thread-3,5,main] Thread[pool-1-thread-2,5,main]
notify() Thread[pool-1-thread-1,5,main]
notifyAll() Thread[pool-1-thread-1,5,main] Thread[pool-1-thread-2,5,main] Thread[pool-1-thread-3,5,main] Thread[pool-1-thread-4,5,main] Thread[pool-1-thread-5,5,main]
Timer canceled
Task2.blocker.prodAll() Thread[pool-1-thread-6,5,main]
Shutting down
*///:~
