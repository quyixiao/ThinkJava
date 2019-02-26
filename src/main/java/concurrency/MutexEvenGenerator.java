package concurrency;//: concurrency/MutexEvenGenerator.java
// Preventing thread collisions with mutexes.
// {RunByHand}

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * **
 * 678 页
 * 使用显式的lock对象
 *      javaSE5 的java.util.concurrrent 类库还包含有定义在java.unit.concurrent.clcks中的显式的互斥机制，Lock对象必须被
 *  显式地创建，锁定和释放，因此它丐内建的锁形式相比，代码缺乏优雅性，但是对于解决某些类型的问题来说，它更加灵活，下面用显式的
 *  Lock重写的是sychronizedEventGenerator.java
 *
 *      MutexEvenGenerator添加了一个被互斥调用的锁，并使用lock()和unlock()方法在t()内部创建了临界资源，当你在使用Lock对象时，将这
 *  里所示的惯用法内部化是很重要的，紧接着对lock()的调用，你恬放置在finally出了名中带有unlock()的try-finally语句中，注意，return语句必须在
 *  try出了名中出现 ，以确保unlock()不会过早发生，从而将暴露给了第二个任务。
 *     尽管try-finally所需的代码比synchronized关键字要多，但是这也代表了显式的Locck对象的优点之一，如果在使用synchronized关键字时，某些事物失败了
 * ，那么就会抛出一个异常，但是你没有机会去做任何清理工作，以维护系统使其牌良好的状态，有了显式的Lock对象，你就可以使用finally出了名，将系统维护在正确的
 * 的状态了。
 *      大体上，当你使用synchronized关键字时，需要定的代码量更少，并用户错误出现的可能性也会降低，因此通常只有在解决特殊问题时，才使用显式的Lock对象，
 * 例如，用synchronized关键字不能尝试着获取锁且最终获取锁会失败，或者尝试着获取锁一段时间，然后放弃它，要实现这些，你必须使用concurrent类库
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
 */
public class MutexEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    private Lock lock = new ReentrantLock();

    public int next() {
        lock.lock();
        try {
            ++currentEvenValue;
            Thread.yield(); // Cause failure faster
            ++currentEvenValue;
            return currentEvenValue;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
} ///:~
