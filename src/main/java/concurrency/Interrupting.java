package concurrency;//: concurrency/Interrupting.java
// Interrupting a blocked thread.

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;


/****
 *  694页
 *  在阻塞时终结
 *      前面示例中的Entrance.run()在其循环中包含对sleep()的调用，我们知道，sleep()最终唤醒
 *  ，而任务也将返回循环的开始部分，去检查canceled标志，从而决定是否跳出循环，但是
 *  sleep()一种情况，它使用任务从执行状态变为被阻塞状态，从而你有必要终止被阻塞的任务。
 *      线程状态
 *      新建（new）：当线程被创建时，它会短暂的处于这种状态，此时它己经分配了必要的
 *  系统资源，并执行了初始化，此刻线程己经有资格获得CPU时间了，之后调度器将把这个线程
 *  转变为可运行状态或阻塞状态
 *      就绪（runnable）: 这种情况下，只要调度器把时间片分配给线程，线程就可以运行，
 *  也就是说，在任意时刻，线程可以运行也可以不运行，只要调度器分配时间片给线程，它就
 *  可以运行，这不同于死亡和阻塞
 *      阻塞（Blocked）: 线程能够运行，但有某个条件阻止它的运行，当线程处于阻塞状态时，
 * 调度器将忽略线程，不会分配给线程任何CPU时间。
 *      死亡（Dead）：处于死亡或终止状态的线程将不再是可调度的并且再也不会得到CPU时间
 * 它的任务已结束，或不再是可运行的，任务死亡的通常方式是从run()方法返回，但是任务的线程还是
 * 会被中断，你将要看到这一点。
 *      进入阻塞状态。
 *  一个任务进入阻塞状态，可能有如下原因
 *      通过调用sleep(milliseconds)使任务进入休眠状态，在这种情况下，任务在指定的时间内不会运行。
 *      你通过调用wait()使线程挂起，直到线程得到了notify()或nofityAll()消息，或者在javaSE5的
 *      java.util.concurrent类库中丢人于的signal()或signalAll()消息，线程才会进入就绪状态，
 *      我们将在稍小的小结中验证这一点。
 *      任务等待某个输入/输出完成。
 *      任务试图在某个对象上调用其同步控制方法，但是对象锁不可用，因为另一个任务已经获得了锁。
 * 在较早的代码中，也可能会看到用suspend()和resume()来阻塞和唤醒线程，在是在现在的java中，
 * 这些方法被废止了，因为可能收藏到死锁，所以本书不讨论这些内容，stop()方法也己经被废
 * 止了，因为它不释放线程获得的锁，并且如果线程处于不一致的状态，受损状态，其他任务可以在这
 * 种状态下浏览并修改它们，这样所产生的问题是微妙而难以被发现的。
 *      现在我们需要查看的问题是，有时你希望能够终止处于阻塞状态的任务，如果对于处于
 * 阻塞状态的任务，你不能等待其到达代码中可以检查其状态值的某一点，因面决定让它主动地终止
 * ，那么你就必须强制这个任务跳出阻塞状态。
 *      正如你所想象的，在runanlble.run()方法的中间打断它，与等待该方法到达对cancel标志的
 * 测试，或者到达程序员准备好离开该方法的其他的一些地方相比，要棘手得多，当你打断被阻塞，
 * 的任务时，可能需要清理资源，正因为这一点，在任务的run()方法中间打断，更像是抛出的异常，
 * 因此在java线程中的这种类型的异常中断中用到的异常，这会滑向异常的不恰当的用法，因为这意味着
 * 你经常用它们来控制执行流程，为了在以这种方式终止任务时，返回众所周知的良好状态，你必须
 * 仔细考虑代码的执行路径，并仔细编写catch子句以正确清除所有事物。
 *      Thread类包含interrupt()方法，因此你可以终止被阻塞的任务，这个方法将设置线程的中断状态
 *  如果一个线程己经被阻塞，或者试图执行一个阻塞操作，那么设置这个线程的中断状态将抛出interuptedException
 *  ，当抛出该异常或者该任务调用Thread.interunpted()时，中断状态将复位，正如你将看到的
 *  ，Thead.interrupted()提供了离开run()循环而不抛出异常的第二种方式。
 *      为了调用interrupt()，你必须持有Thread对象，你可能己经注意到了，新的concurren类库似乎在
 *      避免对Thread对象的直接操作，转而尽量通过Exerrption()调用给它应该去的所有的线程，这么
 * 定Executor的所有任务，然而，你有时也会希望只中断某个单一任务，如果使用executor，那么通过调用submit()
 * 而不是executor()来启动任务，就可以持有该任务的上下文，submit()将返回一个泛型Funtrue<?>，其中一个
 * 未修饰的参数，因为你永远都不会在其上调用get()持有这你将true传递给cancel()，那么它就会用有在该线程上调用的
 * interrupt()以停止这个线程的权限，因此，cannel()是一种中断由Executor启动的单个线程的方式。
 *   下面的示例用Executor展示 了基本的interrrupt()用法。
 *
 *
 *
 * 697页
 *      下面的每个任务都表示了一种不同类型的阻塞，SleepBolck是可中断的阻塞示例，而
 *  IOBlocked和SynchronizedBlocked是不可中断的阻塞示例，这个程序证明I/O和在Synchronized块
 *  上的等待是不可中断的，但是通过浏览代码，你也可以预见到这一点 无论是I/O还是尝试
 *  调用synchronized方法，都需要任何InterruptedException处理器。
 *      前两个类很简单直观，在第一个中run()方法调用了sleep()，而在第二个中调用了read()
 *  但是，为了演示sychronizedBock，我们必须首先获取锁，这是通过在构造器中创建匿名的
 *  Thread类实例来实现的，这个匿名的Thread类的对象通过调用f()获取了对象锁（这个线程必须
 *  有别于为SynchronizedBlock驱动 run()的线程，因为一个线程可以多次获得某个对象锁），由于
 *  f()永远都不返回，因此这个锁永远不会释放，而SynchronizedBlock.run()在试图调用f(),并阻塞
 *  以等待这个锁被释放。
 *      从输出中可以看出，你能够中断对sleep()的调用，（或者任何要求抛出IntrrrputException
 *  的调用），但是，你不能中断正在试图获取sysnchronized锁或者试图执行I/O操作的线程，这有点
 *  令人烦恼，的特别是在创建执行I/O的任务时，因为这意味着I/O具有锁住你的多线程程序的潜在可
 *  能，特别是对于基于Web的程序，这更是关乎利害。
 *
 *
 *
 *
 *
 */
class SleepBlocked implements Runnable {
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            print("InterruptedException");
        }
        print("Exiting SleepBlocked.run()");
    }
}

class IOBlocked implements Runnable {
    private InputStream in;

    public IOBlocked(InputStream is) {
        in = is;
    }

    public void run() {
        try {
            print("Waiting for read():");
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                print("Interrupted from blocked I/O");
            } else {
                throw new RuntimeException(e);
            }
        }
        print("Exiting IOBlocked.run()");
    }
}

class SynchronizedBlocked implements Runnable {
    public synchronized void f() {
        while (true) // Never releases lock
            Thread.yield();
    }

    public SynchronizedBlocked() {
        new Thread() {
            public void run() {
                f(); // Lock acquired by this thread
            }
        }.start();
    }

    public void run() {
        print("Trying to call f()");
        f();
        print("Exiting SynchronizedBlocked.run()");
    }
}

public class Interrupting {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        print("Interrupting " + r.getClass().getName());
        f.cancel(true); // Interrupts if running
        print("Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws Exception {
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        print("Aborting with System.exit(0)");
        System.exit(0); // ... since last 2 interrupts failed
    }
} /* Output: (95% match)
Interrupting SleepBlocked
InterruptedException
Exiting SleepBlocked.run()
Interrupt sent to SleepBlocked
Waiting for read():
Interrupting IOBlocked
Interrupt sent to IOBlocked
Trying to call f()
Interrupting SynchronizedBlocked
Interrupt sent to SynchronizedBlocked
Aborting with System.exit(0)
*///:~
