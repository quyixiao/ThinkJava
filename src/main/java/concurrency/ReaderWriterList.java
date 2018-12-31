package concurrency;//: concurrency/ReaderWriterList.java

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static net.mindview.util.Print.print;


/****
 *761页
 *    readWriteLock对象数据结构相对不频繁地写入，但是多个任务要读取这个数据结构
 * 的这个类情况进行优化，ReadWriteLock使得你可以同时有多个读取者，只要它们都不试图写入
 * 即可，如果写入锁己经被其他的任务持有，那么任何读取者都不能试问，直到这个写锁被确定
 * ，它取决于诸如数据被读频率与被修改的频率相比较的结果，读取和写入操作的时间，锁将更加
 * 复杂，因此知操作并不能带来好处，有多少线程竞争以及是不在多处理机器上运行等因素，最终
 * 唯一可以了解ReadWriteLock是否能够给你的程序带来好处的方式就是用试验来证明
 *      下面是只anfi了ReadWriteLock的最基本用法的示例。
 *       readWriterlist可以持有固定数量的任何类型的对象，你必须向构造器提供所希望的列表
 *  尺寸和组装这个列表时所用的初始对象，set()方法李获取一个写锁，以调用底层的ArrayList.set()
 *  而get()方法要获取一个读锁，以调用底层的ArrayList.get()，另外，get()将检查是不己经有多个
 *  读取者获取了读锁，如果是，则将显示这各读取者的数量，以证明可以有多个读取者获得读锁
 *      注意写入者，注意，写入者的数量卖多少于读取者
 *      如果你在JDK文档中查看ReadTrantReadWriteLock，就会发现还有大量的其他方法可用
 *      涉及公平政策必决策，行问题，这是一个相当复杂的工具，只有当你在搜索可以提高性能的
 *  的方法时，才能应该用到它，你的程序的第一个草案应该使用还是直观的同步，并且只有在必需时引入ReadWriteLock
 *
 *
 * @param <T>
 */
public class ReaderWriterList<T> {
    private ArrayList<T> lockedList;
    // Make the ordering fair:
    private ReentrantReadWriteLock lock =
            new ReentrantReadWriteLock(true);

    public ReaderWriterList(int size, T initialValue) {
        lockedList = new ArrayList<T>(
                Collections.nCopies(size, initialValue));
    }

    public T set(int index, T element) {
        Lock wlock = lock.writeLock();
        wlock.lock();
        try {
            return lockedList.set(index, element);
        } finally {
            wlock.unlock();
        }
    }

    public T get(int index) {
        Lock rlock = lock.readLock();
        rlock.lock();
        try {
            // Show that multiple readers
            // may acquire the read lock:
            if (lock.getReadLockCount() > 1)
                print(lock.getReadLockCount());
            return lockedList.get(index);
        } finally {
            rlock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        new ReaderWriterListTest(30, 1);
    }
}

class ReaderWriterListTest {
    ExecutorService exec = Executors.newCachedThreadPool();
    private final static int SIZE = 100;
    private static Random rand = new Random(47);
    private ReaderWriterList<Integer> list =
            new ReaderWriterList<Integer>(SIZE, 0);

    private class Writer implements Runnable {
        public void run() {
            try {
                for (int i = 0; i < 20; i++) { // 2 second test
                    list.set(i, rand.nextInt());
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                // Acceptable way to exit
            }
            print("Writer finished, shutting down");
            exec.shutdownNow();
        }
    }

    private class Reader implements Runnable {
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    for (int i = 0; i < SIZE; i++) {
                        list.get(i);
                        TimeUnit.MILLISECONDS.sleep(1);
                    }
                }
            } catch (InterruptedException e) {
                // Acceptable way to exit
            }
        }
    }

    public ReaderWriterListTest(int readers, int writers) {
        for (int i = 0; i < readers; i++)
            exec.execute(new Reader());
        for (int i = 0; i < writers; i++)
            exec.execute(new Writer());
    }
} /* (Execute to see output) *///:~
