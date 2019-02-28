//: concurrency/ExplicitCriticalSection.java
// Using explicit Lock objects to create critical sections.
package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/*****
 *
 * 689页
 *      这里复用了CirticalSection.java的绝大部分，辛苦你了那user的使用显式的Lock对象的PairManager类型，ExplicitPaireManager2展示了
 *  如何使用Lock对象来创建临界区，而对store()的调用则在这个临界区的外部。
 *
 *
 *  synchronized块必须给定一个在其上进行同步的对象，并且最合理的方式是，使用其他的方法正在
 *  被调用的当前对象，Synchronized(this),这正是PairManager2所使用的方式，在这种方式中，
 *  如果获得了synchronized块上的锁，那么该对象的其他synchronized方法和临界区就不能被调用了
 *  因此，如果在this同步，临界区的效果就会直接缩小在同步的范围内
 *
 *  在时必须在另一个对象上周一波，但是如果你要这么做，就必须确保所有的相关的任务都是在同一个对象上
 *  同步，的，下面的示例演示了两个任务可以同时进入同一个对象，只要这相对象上的方法是在不同的锁上的
 *  同步的即可
 *
 *
 *
 *
 *
 *  1
 *
 */
// Synchronize the entire method:
class ExplicitPairManager1 extends PairManager {
    private Lock lock = new ReentrantLock();

    public synchronized void increment() {
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            store(getPair());
        } finally {
            lock.unlock();
        }
    }
}

// Use a critical section:
class ExplicitPairManager2 extends PairManager {
    private Lock lock = new ReentrantLock();

    public void increment() {
        Pair temp;
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        } finally {
            lock.unlock();
        }
        store(temp);
    }
}

public class ExplicitCriticalSection {
    public static void main(String[] args) throws Exception {
        PairManager
                pman1 = new ExplicitPairManager1(),
                pman2 = new ExplicitPairManager2();
        CriticalSection.testApproaches(pman1, pman2);
    }
} /* Output: (Sample)
pm1: Pair: x: 15, y: 15 checkCounter = 174035
pm2: Pair: x: 16, y: 16 checkCounter = 2608588
*///:~
