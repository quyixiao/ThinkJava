package concurrency;//: concurrency/CachedThreadPool.java

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/***
 *
 *
 * 656页
 *
 * 使用Eecutor
 *      java SE5的java.util.concurrnet包中的执行器将为你管理Thread对象，从而简化并发编程，Executor在客户端和任务执行之间
 * 提供了一个间接层，与客户端直接执行任务不同，这个中介对象将执行任务，Executor允许你管理异步任务的执行，而无须显式的管理线程
 * 生命周期，Execuotr在java SE5/6中是启动任务的优先方法。
 *      我们可以使用Executor来代替在MoreBasicThreads.java中显示的创建Thread对象，LiftOff对象知道如何运行具体的任务，与命令设计模式
 *  一样，它暴露了要执行的单一方法，ExecutorService 具有服务生命周期的Execuotr例如关闭，知道如何构建恰当的上下文来执行Runnable对象
 *  ，在下面的示例中，CachedThreadPool将为每个任务创建一个线程，注意，ExecutorService对象是使用静态的Executor方法创建的，这个方法可以
 *  确定其Executor类型。
 *
 * 非常觉的情况是，单个的Executor被用来创建和管理系统中的所有的任务。
 *
 *      对shutdown()方法的调用可以防止新任务被提交给这个executor，当前线程 在本例中，即驱动main()的线程，将继续运行在shutdown()被调用之前
 * 提交的样的的任务，这个程序将在Executor中的所有任务完成之后尽快的退出。
 *      你可以很容易的将前面示例中的CacheThreadPoll替换为不同的类型的Execuotr ，FixedThreadPool使用了，限的线程集来执行所提交的任务。
 *
 *
 *
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();
    }
} /* Output: (Sample)
#0(9), #0(8), #1(9), #2(9), #3(9), #4(9), #0(7), #1(8), #2(8), #3(8), #4(8), #0(6), #1(7), #2(7), #3(7), #4(7), #0(5), #1(6), #2(6), #3(6), #4(6), #0(4), #1(5), #2(5), #3(5), #4(5), #0(3), #1(4), #2(4), #3(4), #4(4), #0(2), #1(3), #2(3), #3(3), #4(3), #0(1), #1(2), #2(2), #3(2), #4(2), #0(Liftoff!), #1(1), #2(1), #3(1), #4(1), #1(Liftoff!), #2(Liftoff!), #3(Liftoff!), #4(Liftoff!),
*///:~
