//: concurrency/E06_SleepingTask2.java
// {Args: 5}
/****************** Exercise 6 *****************
 * Create a task that sleeps for a random amount
 * of time between 1 and 10 seconds, then displays
 * its sleep time and exits. Create and run a quantity
 * (given on the command line) of these tasks.
 ***********************************************/
package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/****
 *
 *
 *
 * 660 页
 *
 *
 *
 * 创建一个任务，它将眨眼1 至 10秒之间的随机数量的时间，然后显示它的睡眠时间
 * 并退出，创建并运行一个数量的这种任务
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
 *
 *
 *
 * 线程的优先级将该线程的重要性传递给了调试器，尽管cpu处理现有的线程集的顺序是不确定的，但是调试器将倾向于
 * 让优先执行最高的线程先执行，然而，并不是意味着优先权较低的线程将得不到执行，也就是说，优先权不会导致死锁
 * ，优先级较低的线程仅仅是执行的频率较低
 *
 *
 *
 *
 *  在绝大多数时间里，所有的线程都应该以默认的优先级运行，试图操作线程的优先级能感觉是一种错误
 *
 *
 *  下面是一个演示优先级等级的示例，你可以用getPriority()来读取现有的线程优先级，并在任何日志文件系统都可以通过setPriority()来
 *  修改它
 *
 *
 *
 *
 *
 *
 */
class SleepingTask2 implements Runnable {
    private static Random rnd = new Random();
    private final int sleep_time = rnd.nextInt(10) + 1;

    public void run() {
        try {
            TimeUnit.SECONDS.sleep(sleep_time);
        } catch (InterruptedException e) {
            System.err.println("Interrupted: " + e);
        }
        System.out.println(sleep_time);
    }
}

public class E06_SleepingTask2 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        if (args.length != 1) {
            System.err.println(
                    "Provide the quantity of tasks to run");
            return;
        }
        for (int i = 0; i < Integer.parseInt(args[0]); i++)
            exec.execute(new SleepingTask2());
        Thread.yield();
        exec.shutdown();
    }
}