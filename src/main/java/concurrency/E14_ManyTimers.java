//: concurrency/E14_ManyTimers.java
// {Args: 100}
/****************** Exercise 14 ************************
 * Demonstrate that java.util.Timer scales to large numbers
 * by creating a program that generates many Timer objects
 * that perform some simple task when the timeout completes.
 ******************************************************/
package concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


/****
 *
 *
 * 685
 *
 *
 *
 *
 *
 * 创建一个程序，它可以第一页许多Time对象，这些对象在定时时间到达后将执行某个简单的
 * 任务，用这个程序来证明java.util.timer可以扩展到很大的数目
 *
 *
 *
 *
 * 1
 */
public class E14_ManyTimers {
    public static void main(String[] args) throws Exception {

        int numOfTimers =5000;
        for (int i = 0; i < numOfTimers; i++) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    System.out.println(System.currentTimeMillis());
                }
            }, numOfTimers - i);
        }
        // Wait for timers to expire
        TimeUnit.MILLISECONDS.sleep(2 * numOfTimers);
        System.exit(0);
    }
}