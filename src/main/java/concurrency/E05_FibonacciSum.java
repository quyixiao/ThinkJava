package concurrency;//: concurrency/E05_FibonacciSum.java
/****************** Exercise 5 *****************
 * Modify Exercise 2 so that the task is a Callable
 * that sums the values of all the Fibonacci numbers.
 * Create several tasks and display the results.
 ***********************************************/

import net.mindview.util.Generator;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/***
 * 修改练习2，使用计算所有的斐波纳契数字的数值总和的任务成为Callable，创建多个任务并显示结果
 *
 *
 *
 */
class FibonacciSum
        implements Generator<Integer>, Callable<Integer> {
    private int count;
    private final int n;

    public FibonacciSum(int n) {
        this.n = n;
    }

    public Integer next() {
        return fib(count++);
    }

    private int fib(int n) {
        if (n < 2) return 1;
        return fib(n - 2) + fib(n - 1);
    }

    public Integer call() {
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += next();
        return sum;
    }
}

public class E05_FibonacciSum {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> results =
                new ArrayList<Future<Integer>>();
        for (int i = 1; i <= 10; i++)
            results.add(exec.submit(new FibonacciSum(i)));
        Thread.yield();
        exec.shutdown();
        for (Future<Integer> fi : results)
            try {
                System.out.println(fi.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}