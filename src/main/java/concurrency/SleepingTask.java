package concurrency;//: concurrency/SleepingTask.java
// Calling sleep() to pause for a while.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/****
 *  660 页
 * 影响任务行为的一种简单的方法是调用sleep()，这将使任务中止执行给定的时间，在LiftOff类中，要是把对
 * yield()的调用换成是sleep()，将得到如下结果。
 *
 *
 *      对于sleep()的调用可以抛出interupteException异常，并且你可以的看到，它在run()中被捕获，因为异常不
 * 能跨线程传播main() ,所以你必须在本地处理所有在任务内部产生的异常。
 *      java SE5 引入了更加显式的sleep()版本，作为TimeUnit类的一部分，就像上面的示例所示的那样，这个方法允许你
 * 指定sleep()延迟的时间单元，因此可以提供更好的可阅读性，TimeUnit还可以被用来执行转换，就像稍后你会在本书看到的
 * 那样。
 *      你可能会注意到，这些任务是按照 完美的分布  顺序运行的，即从0到4，然后再回过头从0开始，当然这取决于你的平台。
 * 这是机制到另一个线程，进而驱动另一个任务，但是，顺序行为依赖于底层的线程机制，这种机制在不同的操作系统之间是有差异的
 * ，因此，你不能依赖于它，如果你必须控制执行的顺序，那么最好的押宝就是要不用同步控制或者在某些情况下，压根不使用线程
 * ，但是要编写自己的协例程，这些例程将会按照指定在有顺序在互相之间传递控制权
 *     线程的优先级将该线程的重要性传递给了调度器，尽管CPU处理现有的线程集的顺序是不确定的，但是调度器倾向于优先权最
 * 高的线程先执行，然而，这并不是意味着优先权较低的线程得不到执行，也就是说优先权不会导致死锁，优先级较低的线程仅仅是执行的频率
 * 较低。
 *      在绝大多数时间里，所有的线程都应该以默认的优先级运行，试图操纵线程优先级通常是一种错误。
 *      下面是一个演示优先级的示例，你可能getPriority()来读取现有的线程优先级，并且在任何的时刻都可以通过setPriority()来
 *      修改它。
 *
 *
 *      toString()方法被覆盖，以便使用Thread.toString()方法来打印线程的名称，线程的优先级以及线程所属的线程组 你可以通过构造器
 * 来自己设置这个名称，这里是自动生成的名称，如ppl-1-thread-1,pool-1-thread2 等，覆盖后的toString()方法打印了线程的倒计时数值。
 * 注意，你可以在一个任务内部，通过调用Thread.currentThread() 来获得对驱动该任务的的Thread对象的引用。
 *      可以看到，最后一个线程的优先级最高，其余所有线程的优先级被设为最低,注意优先级是在run()的开头部分设定的，在构造器中设置它们
 * 不会有任何好处，因为Executor在此刻还没有开始执行任务。
 *      在run()方法，执行了100000次开销相当在的浮点运算，包括double类弄的加法与除法，变量d的volatitle的，以努力的确保不进行任何
 *  的编写试一试，把包含double运算的for注释掉，有了这引进运算，就能现观察到优先级为MAX_PRIORITY线程被线程调度器优先选择，至少在
 *  我的windows xp机器上是这样的，尽管向控制台打印也是开销较大的操作，但是在那种情况下看不出优先级效果，因为向控制台打印不能被中断
 *  （否则的话，在多线程情况下控制台显示就套了），而数学运算是可以被中断的，这时运算时间足够的长，因此酷调度机制才来得及介入，交换
 *  任务并关注优先级，使得最高优先级线程优先选择。
 *       尽管JDK有10个优先级，但它与多数操作系统都不能映射得很好，比如，Windows有7个优先级且不是固定的，所以这种映射关系也是不确定的
 *  ，Sun的Solaris有2 个优先级，唯一可移值的方法是当调整优先级的时候，只使用Max_priority,norm_priority和Min_priority三种级别。
 *  让步，
 *      如果知道己经完成在run()方法的循环的一次迭代过程中所需的工作，就可以给线程调度机制一个暗示，你的工作己经做得差不多了，可能让
 *  别的线程使用CPU了，这个暗示将通过调用yield()方法来作出，不过这只是一个暗示，没有任务机制保证它将会被采纳，当调用yield()时，你也是
 *  在建议具有相同优先级的其他的线程可以运行。
 *      LiftOff.java使用yield()在各种LiftOff任务之间产生分布良好的处理机制，尝试着注释掉LiftOff.run()中的Thread.yield() ，以查看
 *  区别，但是，大体上，对于任何重要的控制或在调整应用时，都不能依赖于yield()，实际上，yield()经常被误用。
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
public class SleepingTask extends LiftOff {
    public void run() {
        try {
            while (countDown-- > 0) {
                System.out.print(status());
                // Old-style:
                // Thread.sleep(100);
                // Java SE5/6-style:
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new SleepingTask());
        exec.shutdown();
    }
} /* Output:
#0(9), #1(9), #2(9), #3(9), #4(9), #0(8), #1(8), #2(8), #3(8), #4(8), #0(7), #1(7), #2(7), #3(7), #4(7), #0(6), #1(6), #2(6), #3(6), #4(6), #0(5), #1(5), #2(5), #3(5), #4(5), #0(4), #1(4), #2(4), #3(4), #4(4), #0(3), #1(3), #2(3), #3(3), #4(3), #0(2), #1(2), #2(2), #3(2), #4(2), #0(1), #1(1), #2(1), #3(1), #4(1), #0(Liftoff!), #1(Liftoff!), #2(Liftoff!), #3(Liftoff!), #4(Liftoff!),
*///:~
