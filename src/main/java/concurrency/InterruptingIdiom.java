package concurrency;//: concurrency/InterruptingIdiom.java
// General idiom for interrupting a task.
// {Args: 1100}

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;


/****
 *
 * 701 页
 *
 *
 *  检查中断
 *      注意：当你在线程上调用interrupt()时，中断发生的唯一时刻是在任务要进入到阻塞操作中，
 *  或者己经在阻塞操作内部时（如你所见，除了不可中断的I/O或被阻塞的synchronized方法这外，
 *  在其余的例外情况下，你无可事事）。但是如果根据程序运行的环境，你己经编写了可能会产生
 *  这种调用的代码，那又该怎么办呢，如果你只能通过在阻塞调用上抛出异常来退出，那么你就
 *  无法总是可以离开run()循环，因此，如果你调用interrupt()以停止某个任务，那么在run()
 *  循环碰巧没有产生任何阻塞调用的情况下，你的任务将需要第二种方式来退出。
 *      这种机会是由中断来表示的，其状态可通过调用interrupt()来设置，你可以通过调用
 *  interrupted()来检查中断状态，这不仅可以告诉你interrupt()是否被调用过，而且还可以清除中
 *  断状态，清除中断状态可以确保并发结构不会就截个傻被中断这个问题通知你两次，你可以
 *经由单一的InterruptedExcetion或单一的成功Thread.interrputed测试来得到这种通知，如果
 * 你想要再次检查以了解是否中断，则可以在调用Thread.interrupted()时将结果存储起来
 *      下面的示例展示了典型的惯用法，你应该在run()方法中使用它来处理在中断状态被设置时
 *  被阻塞和各种可能。
 *
 *      NeedsCleanup类强调在你经由异常离开循环时，正确清理资源的必要性，注意，所有在
 *  Blocked3.run()中创建的NeedsCleanup资源都必须在其后面紧跟try-finally子句，以确保
 *  cleanup()方法总是会被调用
 *      你必须给程序提供一个命令行参数，来表示在它调用interrrput()之前以毫秒单位来延迟时
 *   间，通过使用不同的延迟，你可以在不现地点退出Blocked3.run()，在阻塞的sleepr()调用中，或者
 *   在非阻塞的数学计算中，你将看到，如果interrupt()在注释point2()之后，即在非阻塞操作过程中
 *   被调用，那么首先循环将结束。然后所有出了的本地对象钭被销毁，最后循环会经由while语句的
 *   顶部退出，但是，如果interrupt()的point1和point2之间（在while应该够之后，但是在阻塞操
 *   作sleep()之前或其过程）被调用，那么这个任务就会在第一
 *   次试图调用阻塞操作之前，经由interrrputed
 *   Exception退出，在这种情况下，在异常被抛出之时唯一被创建出来的NeedskCleanUP对象将被清除，
 *   而你也就有了在catch子句中执行其他的任何清除工作的机会。
 *      被设计用来响应interrupt()的类必须建立一种策略，来确保它将地保持一到我的状态，这通常意味着
 *  所有需要清理的对象创建操作的后面，都必须紧跟try-finally出了名，从而使得无论run()循环
 *  如何退出，清理都会发生，像这样的代码会工作得很好，但是，唉，由于在java中缺乏自动的析构器调用。
 *  因此这将依赖于客户意见和序员去编写正确的try-finally子句
 *
 *
 *
 *
        注意，当你在线程上高朋和interrupt()时，中断发生的唯一时刻是在任务要进入到阻塞操作中
 *  或者已经在阻塞操作内部时，如你所见，除了不呆中断的I/O或被阻塞的synchronized方法之外
    在其余的例外的情况下，你均可事事，但是如果根据程序运行的环境，你乙级编写了可能产生这种阻塞调用
    代码，那又该怎么办呢，如果你只能通过阻塞用上抛出异常来退出，那么你就无法总是可以离开
 run()循环，因此，如果你调用intteruupt()以停止某个任务，

 被设计用来响应interrupt()的类必须 建立一种策略，来确保它将保持一致的状态，这通常意味
 着所有需要清理的对象创建操作的后面，都必须紧跟try-finaly子句，从而使得无论run()


 wait()，notify()以有notifyALL()有一个比较特殊的方面，那算了就是这些方法在基类Object问他，而不是
 属于Thread的一部分，尽管开始年

 *
 *
 *
 *  1
 *
 *
 */
class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int ident) {
        id = ident;
        print("NeedsCleanup " + id);

    }

    public void cleanup() {
        print("Cleaning up " + id);
    }
}

class Blocked3 implements Runnable {


    private volatile double d = 0.0;

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // point1
                NeedsCleanup n1 = new NeedsCleanup(1);
                // Start try-finally immediately after definition
                // of n1, to guarantee proper cleanup of n1:
                try {
                    print("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    // point2
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    // Guarantee proper cleanup of n2:
                    try {
                        print("Calculating");
                        // A time-consuming, non-blocking operation:
                        for (int i = 1; i < 2500000; i++)
                            d = d + (Math.PI + Math.E) / d;
                        print("Finished time-consuming operation");
                    } finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
            }
            print("Exiting via while() test");
        } catch (InterruptedException e) {
            print("Exiting via InterruptedException");
        }
    }
}



public class InterruptingIdiom {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(
                1000
        );
        t.interrupt();
    }
}







/* Output: (Sample)
NeedsCleanup 1
Sleeping
NeedsCleanup 2
Calculating
Finished time-consuming operation
Cleaning up 2
Cleaning up 1
NeedsCleanup 1
Sleeping
Cleaning up 1
Exiting via InterruptedException
*///:~
