package concurrency;//: concurrency/SimpleMicroBenchmark.java
// The dangers of microbenchmarking.

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/****
 *
 *   748页
 * 21 章
 *      在java SE5的java.util.cncurrent类库中存在着数量庞大的用于性能提高的类，当你细读
 *  concurrent类库时就会发现很难辨认哪些类适用于常规应用，例如BlockingQueue ，而哪些类只
 *  适用于提高性能，在本节中，我们将围绕着性能调优探讨某些话题和类。
 *      比较各类互斥技术
 *   既然java包括老式的synchroized关键字和java SE5中新的Lock和Atominc类，那么比较这些
 *   不同的方式，更多的理解它们各自的价值和适用范围，就会显得很有意义
 *      比较天真的方式是在针对每种方式都执行一个简单的测试，就像下面的这样
 *      从输出中可以看到，对synchronized方法的调用看起来要比使用ReentrantLock快，这是为
 *   什么呢。
 *      本例演示了所谓的 微基准测试，危险这个术语通常指在隔离的，脱离上下文环境的情况正对某个特性
 *   能测试，当然，你仍旧必须编写测试来难诸如 Lock 比synchronized更快
 *      这样的断言，但是你需要在编写这些测试的时候意识到，在编写过程中和运行时实际会
 *   发生什么
 *      上面的示例存在着大量的问题，首先也是重要的是，我们只有在这些互斥存在竞争的情况
 *   下，才能看到真正的性能差异，因此必须有多个任务尝试着访问互斥代码区，而在上面的示例中
 *   ，每个互斥都是由单个的main()线程在隔离的情况下测试的。
 *      其次，当编译器看到synchronized关键字时，有可能会执行特殊的优化，甚至有可能会注意到
 *   这个程序是单线程的。编译器甚至可能会识别出counter被递增的次数的固定数量的，因此会
 *   预先讲得出其结果，不同的编译器和运行时系统在这方面会所差异，因此很难确切的了解将
 *   会发生什么，但是我们需要防止编译器去聚尚结果的可能性。
 *      为了创建有效的测试，我们必须使程序更加的复杂，首先我们需要多个任务，但并不只会修改
 *   内部的值的任务，还包括读取这些值的任务，否则优化器可以识别出这些值从来都不会被使用
 *   另外，计算足够复杂的，首先的和不可预测，以使得编译器没有机会执行积极优化，这可以通过
 *   预加载一个大型的随机的int数组，预加载可以减小在主循环上调用Random.next()所千万的
 *   影响，并在计算总和时使用它们实现。
 *
 *
 */
abstract class Incrementable {
    protected long counter = 0;

    public abstract void increment();
}

class SynchronizingTest extends Incrementable {
    public synchronized void increment() {
        ++counter;
    }
}

class LockingTest extends Incrementable {
    private Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            ++counter;
        } finally {
            lock.unlock();
        }
    }
}

public class SimpleMicroBenchmark {
    static long test(Incrementable incr) {
        long start = System.nanoTime();
        for (long i = 0; i < 10000000L; i++)
            incr.increment();
        return System.nanoTime() - start;
    }

    public static void main(String[] args) {
        long synchTime = test(new SynchronizingTest());
        long lockTime = test(new LockingTest());
        System.out.printf("synchronized: %1$10d\n", synchTime);
        System.out.printf("Lock:         %1$10d\n", lockTime);
        System.out.printf("Lock/synchronized = %1$.3f",
                (double) lockTime / (double) synchTime);
    }
} /* Output: (75% match)
synchronized:  244919117
Lock:          939098964
Lock/synchronized = 3.834
*///:~
