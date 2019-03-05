//: concurrency/E21_ThreadCooperation.java
/****************** Exercise 21 ******************
 * Create two Runnables, one with a run() that
 626 Thinking in Java, 4th Edition Annotated Solution Guide
 * starts and calls wait(). The second class should
 * capture the reference of the first Runnable object.
 * Its run() should call notifyAll() for the first
 * task after some number of seconds have passed so
 * that the first task can display a message. Test
 * your classes using an Executor.
 ***********************************************/
package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;


/****
 *
 *
 *
 *  706
 *
 *
 *
 *  创建两个Runnbalbel，其中一个run()方法启动并调用wait()，而第二个类应该
 *  捕获第一个Runnable对象的引用，其run()方法应该在一定的秒数之后，为第一个任务调用
 *  notifyAll()，从而使得第一个任务可以显示和条信息，使用Executor来测试你的类
 *
 *
 *       使用Executor来测试你的类
 *
 *
 *
 *
 *
 */
class Coop1 implements Runnable {
    public Coop1() {
        print("Constructed Coop1");
    }

    public void run() {
        print("Coop1 going into wait");
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException ignore) {
            }
        }
        print("Coop1 exited wait");
    }
}

class Coop2 implements Runnable {
    Runnable otherTask;

    public Coop2(Runnable otherTask) {
        this.otherTask = otherTask;
        print("Constructed Coop2");
    }

    public void run() {
        print("Coop2 pausing 5 seconds");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ignore) {
        }
        print("Coop2 calling notifyAll");
        synchronized (otherTask) {
            otherTask.notifyAll();
        }
    }
}

public class E21_ThreadCooperation {
    public static void main(String args[]) throws Exception {



        Runnable coop1 = new Coop1(), coop2 = new Coop2(coop1);

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(coop1);
        exec.execute(coop2);

        Thread.yield();
        exec.shutdown();




    }
}