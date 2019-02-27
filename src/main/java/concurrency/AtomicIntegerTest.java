package concurrency;
//: concurrency/AtomicIntegerTest.java

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


/******
 * 684页
 * 原子类
 *      java se5 引入了诸如atomicintegeer automicLong,AtomicReferece等特殊的原子性变量类，它们提供下面形式的原子性
 * 更新的操作
 *      boolean compareAndSet（expecteValue,updateValue）
 * 这些类被调整为可以使用在某些现代处理器上的可获得的，并且是在机器 级别上的原子性，因此在使用它们时，通常不需要担心，对于常规编程
 * 来说，它们很少会派上用场，但是在涉及性能调优时，它们就大有用武之地了，例如，我们可以使用AomicInterge来重写AtomicCityTest.java
 *
 *
 *
 * 1
 *
 */
public class AtomicIntegerTest implements Runnable {
    private AtomicInteger i = new AtomicInteger(0);

    public int getValue() {
        return i.get();
    }

    private void evenIncrement() {
        i.addAndGet(2);
    }

    public void run() {
        while (true)
            evenIncrement();
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 5000); // Terminate after 5 seconds
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest ait = new AtomicIntegerTest();
        exec.execute(ait);
        while (true) {
            int val = ait.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
} ///:~
