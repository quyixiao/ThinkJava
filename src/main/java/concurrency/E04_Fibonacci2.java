//: concurrency/E04_Fibonacci2.java
/****************** Exercise 4 *****************
 * Repeat Exercise 2 using the different types of
 * executors shown in this section.
 ***********************************************/
package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class E04_Fibonacci2 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 1; i <= 5; i++)
            exec.execute(new Fibonacci(i));
        Thread.yield();
        exec.shutdown();
        exec = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 5; i++)
            exec.execute(new Fibonacci(i));
        Thread.yield();
        exec.shutdown();
        exec = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 5; i++)
            exec.execute(new Fibonacci(i));
        Thread.yield();
        exec.shutdown();
    }
}