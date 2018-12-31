package concurrency;//: concurrency/ThreadLocalVariableHolder.java
// Automatically giving each thread its own storage.

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/*****
 *  690
 *  线程本地存储
 *      防止任务在共享产生冲突的第二种方法是根除对变量的共享，线程本地存储是一种
 *  自动化的机制，可以为使用相同的变量的每个不同的线程都创建不同的存储，因此，如果你有5个线程
 *  都要使用变量x所表示的对象，那和结束本地在就会生成5个用于x的不同的存储块，主要是它们使得
 *  你可能将状态与线程关联起来。
 *      创建和管理线程本地存储可以由java.lang.ThreadLoal类来实现，如下所示。
 *
 *
 *
 *      threadLoacal对象通常当作 静态域存储，在创建ThreadLoacal时，你只能通过get()和set()方法
 *  法来访问该对象的内容，其中，get()方法将返回其他的线程相关联的对象的副本，而set()会将参数
 *  插入到为其线程存储的对象中，并返回存储中原有的对象，increment()和get()方法在ThreadLoacalVariableHolder
 *  中演示了这一点，注意，increment()和get()方法都不是synchronized的，
 *  因为Thread保证不会出现竞争条件
 *      当运行这个程序时，你可以看到每个单独的线程都被分配了自己的存储，因为它们每个都
 *  需要跟踪自己的计数值，即便只有一个ThreadLocalVariableHolder对象。
 *
 *
 *
 */
class Accessor implements Runnable {
    private final int id;

    public Accessor(int idn) {
        id = idn;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    public String toString() {
        return "#" + id + ": " +
                ThreadLocalVariableHolder.get();
    }
}

public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value =
            new ThreadLocal<Integer>() {
                private Random rand = new Random(47);

                protected synchronized Integer initialValue() {
                    return rand.nextInt(10000);
                }
            };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Accessor(i));
        TimeUnit.SECONDS.sleep(3);  // Run for a while
        exec.shutdownNow();         // All Accessors will quit
    }
} /* Output: (Sample)
#0: 9259
#1: 556
#2: 6694
#3: 1862
#4: 962
#0: 9260
#1: 557
#2: 6695
#3: 1863
#4: 963
...
*///:~
