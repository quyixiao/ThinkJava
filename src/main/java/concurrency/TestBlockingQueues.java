package concurrency;//: concurrency/TestBlockingQueues.java
// {RunByHand}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import static net.mindview.util.Print.print;

/*****
 *
 *
 * 714页
 *      wait()和notifyAll()方法以一种非常低级的方式解决了任务的操作问题，即每次交互的时都握
 *  手，在许多情况下，你可以瞄向更高的抽象级别，使用同步队列来解决任务协作问题，同步队
 *  列在任何时刻只允许一个任务插入或移除元素，在java.util.concurrent.BlockingQueue接口中
 *  提供了一个队列，这个接口有大量的标准实现，你通常可以使用LinkedBlockingQueue,它是一
 *  个无届队列，还可以使用ArrayBlickingQueue,它具有固定的尺寸，因此你可以在它被阻塞之前，向其中
 *  放置有限数量的元素。
 *      如果消费任务试图从队列中获取对象，而该队列此时为空，那么这些队列还可以挂起消
 *  费者任务，并且当有更多的元素可用时恢复消费者任务，阻塞队列可以解决非常大量的问题
 *  而其方式与wait()和nofityAll()相比，则简单并可靠得多。
 *      下面是一个简单的测试，它将多个LiftOff对象的执行串行化，消费者是LiftOffRunner
 *  它将每个liftOff对象从BlockingQueue中推出并直拉运行，即 它通过显式地调用run()而使用。
 *   自己的线程来运行，而不是为每个任务启动一个新的线程。
 *      各个任务由main()放置到了BlockingQueue中，并且由LiftOffRunner从BlockingQueue中取出
 *  注意，LiftOffrunner可以忽略同步问题，因为它们己经由BlockingQueue解决了。
 *
 *
 * 各个任务main()放置到了BlockingQueue中，并且由LiftOffRunner从BlockingQueue中取出，
 * 注意，LiftOffRunnner可能忽略同上问题，因为它们已经由BlokingQueue解决了
 *
 *
 *
 *
 *
 *
 *  public void main(){
 *  }
 *
 *
 */
class LiftOffRunner implements Runnable {

    private BlockingQueue<LiftOff> rockets;

    public LiftOffRunner(BlockingQueue<LiftOff> queue) {
        rockets = queue;
    }

    public void add(LiftOff lo) {
        try {
            rockets.put(lo);
        } catch (InterruptedException e) {
            print("Interrupted during put()");
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                LiftOff rocket = rockets.take();
                rocket.run(); // Use this thread
            }
        } catch (InterruptedException e) {
            print("Waking from take()");
        }
        print("Exiting LiftOffRunner");
    }
}

public class TestBlockingQueues {
    static void getkey() {
        try {
            // Compensate for Windows/Linux difference in the
            // length of the result produced by the Enter key:
            new BufferedReader(
                    new InputStreamReader(System.in)).readLine();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void getkey(String message) {
        print(message);
        getkey();
    }

    static void
    test(String msg, BlockingQueue<LiftOff> queue) {
        print(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread t = new Thread(runner);
        t.start();
        for (int i = 0; i < 5; i++)
            runner.add(new LiftOff(5));
        getkey("Press 'Enter' (" + msg + ")");
        t.interrupt();
        print("Finished " + msg + " test");
    }

    public static void main(String[] args) {
        test("LinkedBlockingQueue", // Unlimited size
                new LinkedBlockingQueue<LiftOff>());
        test("ArrayBlockingQueue", // Fixed size
                new ArrayBlockingQueue<LiftOff>(3));
        test("SynchronousQueue", // Size of 1
                new SynchronousQueue<LiftOff>());
    }
} ///:~
