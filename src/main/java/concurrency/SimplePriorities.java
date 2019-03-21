package concurrency;//: concurrency/SimplePriorities.java
// Shows the use of thread priorities.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/***
 * toString()方法被覆盖，以便使用Thread.toString()方法来打印线程的，如线程所属的线程组，你可以通过构造器来
 * 设置这个名称，这里就是自动生成的名称，如
 * pool-1-thread1,pool-1-thread2等，覆盖后的toString()打印了线程倒计数值，注意，你可
 * 以在一个任务的内部，通过调用Thread.cuurentThread()来获得对欢动该任务的Thread对象的引用
 *
 *  可以看到，最后一个线程优先级最高，其余所有的线程的优先级被设为最低，注意，优先级是在run()的开关部分
 *  设定的，在构造器中设置它们不会有任何好处，因为Executrok在此刻还没没有开始执行任务
 *
 *
 * 在run()里，执行了100000次开销相当大的浮点运算，包括double类型的加法与除法，变量d
 * 是volatile的，以努力确保不进行任何编译器优化，如果没有加入边些运算的话，就看到到就默默优先级的效果，
 * 试一试，所包含double运算的for的循环注释掉，有了这些运算，就能观察到优先级为Max_proiority的线程被线程调试器优先选择，
 * 至少在我的window XP机器上是这样的，
 * 以文害辞向控制台打印也是开销较大的操作，但在那种情况下看不出优先级的效果，因为向控制台打印不能被中断，否则的话，在多线程情况下
 * 控制台显示就乱套了，而数学运算是可以中断的，这里运算时间足够的长，因此线程调试机制才来得及介入，交换任务并关注优先级，使得最高
 * 优先级线程被优先选择
 *
 *  尽管jdk有10个优先级，但它与多数操作系统都映射得很好，比如，Window个优先级且不是固定的，所以这种映射关系也是不确定的
 *  ，sun的solaris有2个优先级，唯一可移植的方法是当调整优先级的时候，只使用max_priority，norm_priority和min_pririty三种级别
 *
 *
 *
 *
 *
 */
public class SimplePriorities implements Runnable {


    private int countDown = 5;
    private volatile double d; // No optimization



    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    public String toString() {

        return Thread.currentThread() + ": " + countDown;


    }

    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            // An expensive, interruptable operation:
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();


        for (int i = 0; i < 5; i++) {
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }


        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));



        exec.shutdown();
    }
}





/* Output: (70% match)
Thread[pool-1-thread-6,10,main]: 5
Thread[pool-1-thread-6,10,main]: 4
Thread[pool-1-thread-6,10,main]: 3
Thread[pool-1-thread-6,10,main]: 2
Thread[pool-1-thread-6,10,main]: 1
Thread[pool-1-thread-3,1,main]: 5
Thread[pool-1-thread-2,1,main]: 5
Thread[pool-1-thread-1,1,main]: 5
Thread[pool-1-thread-5,1,main]: 5
Thread[pool-1-thread-4,1,main]: 5
...
*///:~
