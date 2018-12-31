package concurrency;//: concurrency/SynchronizationComparisons.java
// Comparing the performance of explicit Locks
// and Atomics versus the synchronized keyword.

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printf;


/*****
 *      753页
 *      这个程序使用的模版方法模式，将所有的共用代码都放置到基类中，并将所有的不同代码
 *  隔离在导出类的accumulate()和read()的实现中，在每个导出类synchronizedTest.LockTest和
 *  AtomicTest中，你可以看到accmulate()和read()如何表达了实现的互斥现象的不同的方式
 *      在这个程序中，各个任务都是经由FixedThreadPool执行的，在执行过程中尝试着在开始时
 *  跟踪的性能线程的创建，并且在测试过程中防止产生任何额外的开销，为了保险起见，初始测试
 *  执行了两次，而第一次的结果被丢弃，因为它包含了初始线程创建。
 *      程序中必须有一个cyclicBarrier，国为我们有希望确保所有的任务在声明每个测试完成之前
 *  都己经完成
 *      每次调用accumulate()时，它都会儿去到preloaded数组的下一个位置，到达数组尾部时再
 *  了在Accumulator对象上竞争
 *      注意，在AtomicTest中，我发现情况过于复杂，使用Atomic对象己经不适合了---基本上。
 *  如果涉及多个Atomic对象，你就有可能会被强制要求放弃这种用法，转而使用更加常规的互斥
 *  JDK文档特别声明，当对一个对象的临界更新被限制为单个变量时，只有使用Atomic对象这种方式
 *  才能工作，但是，这个测试仍旧保留下来，使你能够感受到Atomic对象的性能优势。
 *      在main（）中，测试是重复运行的，并且你可以要求其重复次数超过5次（默念次数），对于每次
 *  重复测试的第一的数量都会加倍，因此你可以看到运行次数越来越多时，这此不同的超过门槛值之后
 *  synchronied关键字变得非常你将，而Locck和atomic则显得大体维持着与BaseLIne测试之间的比例
 *  关系，因此也就变得比synchronized关键字要高效得多
 *      记住，这个程序只给出了各种互斥方式之间的差异趋势，而上面的输出也仅仅都表示了这些
 *  差异在我们特定环境下的特定机器上的表现，在行为方面肯定会存在关明显的变化，例如，某个些
 *  hotspot运行时优化会在程序运行数分钟之后被调用，但是对于服务器程序，这段时间可能会
 *  长达数小时。
 *      也就是说，很明显，使用Lock通常会比使用synchroized要高效许多，而且synchronized的
 *  开销看起来变化范围太大，而Lock相对比较一致。
 *      这是否意味着，你永远都不应该使用synchronized关键字呢，这里有两个因素需要考虑，首先，
 *  在synchroiznedtioncomparesion.java中，互斥方法的方法体是非常小的，通常，这是一个很好
 *  的习惯，只互斥那些你绝对必须互斥的部分，但是，在实际中，被互斥部分可能会比上面示例中
 *  的那些大许多，因此在这些方法体中花费的时间的百分比可能会明显大于进入姨妈服诉开销，
 *  这样也就学校没了提高互斥的速度所有的好处，当然唯一了角这一点的方式是当你在对
 *  性能调优时，应该立即-尝试各种不同的方法并观察它们千万的影响。
 *      其次，阅读本章中的代码就会发现，很明显，synchozedd关键字所产生的代码，与Lock所需
 *  的别锁 ty-finally解锁，惯用法所产生的代码相比，可读性提高了很多，这就是为什么本章
 *  主要使用synchrozied关键字的原因，就像我在本书其他地方提到过，代码被阅读的次数远多于
 *  被编写的次数，在编程时，与其他从交流相对于与计算机交流而言，要重要得多，因此代码的
 *  被编写的次数，在编程时，可读必到头重要，因此，以synchronized关键字入手，只胡在性能调优时
    才替换为Lock对象，这种做法，是且有实际意义的。
        最后，当你在自己的并发程序中可以使用Atomic类时，这肯定非常好，但是要意识到，正如我们
    在synchronizedonCompareons.java所看到的，Atomic对象并且这个对象独立于其他所有的对象，
    更安全的做法是，以更加传统的互斥方式入手，只有性能方面的需求能够明确指示时，
    再替换为Atomic.

 *
 */
abstract class Accumulator {
    public static long cycles = 50000L;
    // Number of Modifiers and Readers during each test:
    private static final int N = 4;
    public static ExecutorService exec =
            Executors.newFixedThreadPool(N * 2);
    private static CyclicBarrier barrier =
            new CyclicBarrier(N * 2 + 1);
    protected volatile int index = 0;
    protected volatile long value = 0;
    protected long duration = 0;
    protected String id = "error";
    protected final static int SIZE = 100000;
    protected static int[] preLoaded = new int[SIZE];

    static {
        // Load the array of random numbers:
        Random rand = new Random(47);
        for (int i = 0; i < SIZE; i++)
            preLoaded[i] = rand.nextInt();
    }

    public abstract void accumulate();

    public abstract long read();

    private class Modifier implements Runnable {
        public void run() {
            for (long i = 0; i < cycles; i++)
                accumulate();
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class Reader implements Runnable {
        private volatile long value;

        public void run() {
            for (long i = 0; i < cycles; i++)
                value = read();
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void timedTest() {
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            exec.execute(new Modifier());
            exec.execute(new Reader());
        }
        try {
            barrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        duration = System.nanoTime() - start;
        printf("%-13s: %13d\n", id, duration);
    }

    public static void
    report(Accumulator acc1, Accumulator acc2) {
        printf("%-22s: %.2f\n", acc1.id + "/" + acc2.id,
                (double) acc1.duration / (double) acc2.duration);
    }
}

class BaseLine extends Accumulator {
    {
        id = "BaseLine";
    }

    public void accumulate() {
        value += preLoaded[index++];
        if (index >= SIZE) index = 0;
    }

    public long read() {
        return value;
    }
}

class SynchronizedTest extends Accumulator {
    {
        id = "synchronized";
    }

    public synchronized void accumulate() {
        value += preLoaded[index++];
        if (index >= SIZE) index = 0;
    }

    public synchronized long read() {
        return value;
    }
}

class LockTest extends Accumulator {
    {
        id = "Lock";
    }

    private Lock lock = new ReentrantLock();

    public void accumulate() {
        lock.lock();
        try {
            value += preLoaded[index++];
            if (index >= SIZE) index = 0;
        } finally {
            lock.unlock();
        }
    }

    public long read() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }
}

class AtomicTest extends Accumulator {
    {
        id = "Atomic";
    }

    private AtomicInteger index = new AtomicInteger(0);
    private AtomicLong value = new AtomicLong(0);

    public void accumulate() {
        // Oops! Relying on more than one Atomic at
        // a time doesn't work. But it still gives us
        // a performance indicator:
        int i = index.getAndIncrement();
        value.getAndAdd(preLoaded[i]);
        if (++i >= SIZE)
            index.set(0);
    }

    public long read() {
        return value.get();
    }
}

public class SynchronizationComparisons {
    static BaseLine baseLine = new BaseLine();
    static SynchronizedTest synch = new SynchronizedTest();
    static LockTest lock = new LockTest();
    static AtomicTest atomic = new AtomicTest();

    static void test() {
        print("============================");
        printf("%-12s : %13d\n", "Cycles", Accumulator.cycles);
        baseLine.timedTest();
        synch.timedTest();
        lock.timedTest();
        atomic.timedTest();
        Accumulator.report(synch, baseLine);
        Accumulator.report(lock, baseLine);
        Accumulator.report(atomic, baseLine);
        Accumulator.report(synch, lock);
        Accumulator.report(synch, atomic);
        Accumulator.report(lock, atomic);
    }

    public static void main(String[] args) {
        int iterations = 5; // Default
        if (args.length > 0) // Optionally change iterations
            iterations = new Integer(args[0]);
        // The first time fills the thread pool:
        print("Warmup");
        baseLine.timedTest();
        // Now the initial test doesn't include the cost
        // of starting the threads for the first time.
        // Produce multiple data points:
        for (int i = 0; i < iterations; i++) {
            test();
            Accumulator.cycles *= 2;
        }
        Accumulator.exec.shutdown();
    }
} /* Output: (Sample)
Warmup
BaseLine     :      34237033
============================
Cycles       :         50000
BaseLine     :      20966632
synchronized :      24326555
Lock         :      53669950
Atomic       :      30552487
synchronized/BaseLine : 1.16
Lock/BaseLine         : 2.56
Atomic/BaseLine       : 1.46
synchronized/Lock     : 0.45
synchronized/Atomic   : 0.79
Lock/Atomic           : 1.76
============================
Cycles       :        100000
BaseLine     :      41512818
synchronized :      43843003
Lock         :      87430386
Atomic       :      51892350
synchronized/BaseLine : 1.06
Lock/BaseLine         : 2.11
Atomic/BaseLine       : 1.25
synchronized/Lock     : 0.50
synchronized/Atomic   : 0.84
Lock/Atomic           : 1.68
============================
Cycles       :        200000
BaseLine     :      80176670
synchronized :    5455046661
Lock         :     177686829
Atomic       :     101789194
synchronized/BaseLine : 68.04
Lock/BaseLine         : 2.22
Atomic/BaseLine       : 1.27
synchronized/Lock     : 30.70
synchronized/Atomic   : 53.59
Lock/Atomic           : 1.75
============================
Cycles       :        400000
BaseLine     :     160383513
synchronized :     780052493
Lock         :     362187652
Atomic       :     202030984
synchronized/BaseLine : 4.86
Lock/BaseLine         : 2.26
Atomic/BaseLine       : 1.26
synchronized/Lock     : 2.15
synchronized/Atomic   : 3.86
Lock/Atomic           : 1.79
============================
Cycles       :        800000
BaseLine     :     322064955
synchronized :     336155014
Lock         :     704615531
Atomic       :     393231542
synchronized/BaseLine : 1.04
Lock/BaseLine         : 2.19
Atomic/BaseLine       : 1.22
synchronized/Lock     : 0.47
synchronized/Atomic   : 0.85
Lock/Atomic           : 1.79
============================
Cycles       :       1600000
BaseLine     :     650004120
synchronized :   52235762925
Lock         :    1419602771
Atomic       :     796950171
synchronized/BaseLine : 80.36
Lock/BaseLine         : 2.18
Atomic/BaseLine       : 1.23
synchronized/Lock     : 36.80
synchronized/Atomic   : 65.54
Lock/Atomic           : 1.78
============================
Cycles       :       3200000
BaseLine     :    1285664519
synchronized :   96336767661
Lock         :    2846988654
Atomic       :    1590545726
synchronized/BaseLine : 74.93
Lock/BaseLine         : 2.21
Atomic/BaseLine       : 1.24
synchronized/Lock     : 33.84
synchronized/Atomic   : 60.57
Lock/Atomic           : 1.79
*///:~
