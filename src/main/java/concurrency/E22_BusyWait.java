//: concurrency/E22_BusyWait.java
/****************** Exercise 22 ***************************
 * Create an example of a busy wait. One task sleeps for a
 * while and then sets a flag to true. The second task
 * watches that flag inside a while loop (this is the busy
 * wait) and when the flag becomes true, sets it back to
 * false and reports the change to the console. Note how
 * much wasted time the program spends inside the busy wait,
 * and create a second version of the program that uses
 * wait() instead of the busy wait.
 ********************************************************/
package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/*****
 *
 * 706
 *
 *
 *  创建一个忙等侍的示例，第一个任务休眠一段时间然后将一个标志设置为true,而第二个任务在一个while循环中
 *  观察这个标志，这就是忙等待，并且当该标志为true时，将其设置为false,然后向控制台报告这个变化，请注意程序
 *  在忙等待中浪费了多少时间，然后创建该程序的第二个版本，其中将使用wait()而不是忙等待
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * 1
 *
 */
public class E22_BusyWait {
    private static volatile boolean flag;
    private static int spins;

    public static void main(String[] args) throws Exception {
        Runnable r1 = new Runnable() {
            public void run() {
                for (; ; ) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        return;
                    }
                    flag = true;
                }
            }
        };
        Runnable r2 = new Runnable() {
            public void run() {
                for (; ; ) {
                    // The busy-wait loop
                    while (!flag &&
                            !Thread.currentThread().isInterrupted())
                        spins++;
                    System.out.println("Spun " + spins + " times");
                    spins = 0;
                    flag = false;
                    if (Thread.interrupted())
                        return;
                }
            }
        };
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(r1);
        exec.execute(r2);
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
    }
}