package concurrency;//: concurrency/CountDownLatchDemo.java

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;


/****
 *
 * 723页
 *
 *    它被用来同步一个或多个任务，强制它等待由其他任务执行的一组操作完成。
 *      你可以向CountDownLatch对象设置一个初始值，任何在这个对象上调用wait()的方法都
 *  将阻塞，直至这个计数值到达0，其他的任务在结束其工作时，可以在该对象上调用countDown()来
 *  减小这个计数值，CountDownLatch被设计为只触发一次，计数值不能被重置，如果你需要能够
 *  重置计数值版本，则可以使用CyclicBarrier.
 *      调用countDown()的任务在产生这个调用时并没有被阻塞，只有对await()的调用被阻塞，
 *  直至计数器的值到达0 。
 *      countDownLatch的典型用法是将一个程序分为n个互相独立的可解决任务，并创建值为0的
 *  CountDownLatch,当每个任务完成时，都会在这个锁存器上调用countDown()，等待问题被解决
 *  的任务在这个锁存器上调用await()，它们自己拦住，直到锁存器计数结束，下面是演示这种技术
 *  的一个框架示例。
 *      必须等待部分，它要等待到问题的初始部分完成为止，所有任务都作用了在main()中定义的同
 *  一个单一的CountDownLatch
 *      类库的线程安全。
 *      注意，TaskPortion包含了一个静态的Randon对象，这意味着多个可能会同时调用。
 *  Random.nextInt()这是否安全。
 *      如果存在问题，在这种情况下，可以通过向TaskPortion提供自己的Randon对象来解决，
 *  也就是说，通过移除static限定符的方式解决，但是这个问题对于java标准类库中的方法来说
 *  也都存在，哪些线程是安全的，哪些不是安全的。
 *      遗憾的是，JDK文档并没有指出这一点，Random.nextInt()碰巧是安全的，但是，你必须通
 *  过使用Web引擎，或者审视java类库代码，去逐个的提示这一点，这对于被设计为支持的，至少理
 *  论上支持并发的程序设计语言来说，并非是一件好事。
 *
 *
 *
 *
 */

// Performs some portion of a task:
class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static Random rand = new Random(47);
    private final CountDownLatch latch;

    TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException ex) {
            // Acceptable way to exit
        }
    }

    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        print(this + "completed");
    }

    public String toString() {
        return String.format("%1$-3d ", id);
    }
}

// Waits on the CountDownLatch:
class WaitingTask implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;

    WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            latch.await();
            print("Latch barrier passed for " + this);
        } catch (InterruptedException ex) {
            print(this + " interrupted");
        }
    }

    public String toString() {
        return String.format("WaitingTask %1$-3d ", id);
    }
}

public class CountDownLatchDemo {
    static final int SIZE = 100;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        // All must share a single CountDownLatch object:
        CountDownLatch latch = new CountDownLatch(SIZE);
        for (int i = 0; i < 10; i++)
            exec.execute(new WaitingTask(latch));
        for (int i = 0; i < SIZE; i++)
            exec.execute(new TaskPortion(latch));
        print("Launched all tasks");
        exec.shutdown(); // Quit when all tasks complete
    }
} /* (Execute to see output) *///:~
