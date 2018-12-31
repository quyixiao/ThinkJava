package concurrency;//: concurrency/FixedThreadPool.java

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/***
 *
 *     637页
 *
 *     有了FixedThreadPoll，你就可以一次性预先执行代价高昂的线程分配，因而也就可以限制线程数量了，这可以节省时间。
 *  因而也就可以限制线程的数量了。这可以节省时间，因为你不用为每个任务都固定地付出创建线程的开销，在事件驱动的系统中，
 *  需要线程事件处理器，通过直接从池中获取线程，也可以如你所愿地尽快的得到服务，你不会滥用可获得的资源，因为FixedThreadPool使用的Thread对象
 *  的数量是有界的。
 *      注意，在任何线程池中，现有的线程在可能的情况下，都会被自动的复用
 *      尽管本书将使用CachedTheadPool,但是也应该考虑在产生线程的代码中使用FixedThreadPool，CachedThreadPool在程序执行
 *  过程中通常会创建与所需数量是相同的线程，然后它回收线程时停止创建样的线程，因此它是合理的Executor的首选，只有当这种方式会
 *  引发问题时，你才需要切换到FixedThreadPool
 *      SingleThreadExecutor 就像是线程数量为1的FixedThreadPool，这对于你希望在另一个线程中，连续运行的事物，长期存活的任务
 *  来说，都是很有用的，例如监听进入的百万套接字连接任务，或者是事件分发线程
 *      如果向SingleThreadExecutor提交了多个任务，那么这些任务将排队，每个任务都会在下一个任务开始之前运行结束，所有的任务将使用相同的
 *  线程，在下面的示例中，你可以看到每个任务都是按照它们被提交的顺序，并会会议维护它自己 （隐藏）的悬挂任队列。
 *
 *
 *
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        // Constructor argument is number of threads:
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();
    }
} /* Output: (Sample)
#0(9), #0(8), #1(9), #2(9), #3(9), #4(9), #0(7), #1(8), #2(8), #3(8), #4(8), #0(6), #1(7), #2(7), #3(7), #4(7), #0(5), #1(6), #2(6), #3(6), #4(6), #0(4), #1(5), #2(5), #3(5), #4(5), #0(3), #1(4), #2(4), #3(4), #4(4), #0(2), #1(3), #2(3), #3(3), #4(3), #0(1), #1(2), #2(2), #3(2), #4(2), #0(Liftoff!), #1(1), #2(1), #3(1), #4(1), #1(Liftoff!), #2(Liftoff!), #3(Liftoff!), #4(Liftoff!),
*///:~
