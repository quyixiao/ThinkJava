//: concurrency/E03_Runnable2.java
/****************** Exercise 3 *****************
 * Repeat Exercise 1 using the different types of
 * executors shown in this section.
 ***********************************************/
package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class E03_Runnable2 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Printer());
        Thread.yield();
        exec.shutdown();
        exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++)
            exec.execute(new Printer());
        Thread.yield();
        exec.shutdown();
        exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++)
            exec.execute(new Printer());
        Thread.yield();
        exec.shutdown();
    }
}