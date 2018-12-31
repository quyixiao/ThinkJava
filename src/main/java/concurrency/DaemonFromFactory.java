package concurrency;//: concurrency/DaemonFromFactory.java
// Using a Thread Factory to create daemons.

import net.mindview.util.DaemonThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;


/***
 *
 *
 * 663页
 *
 *
 * 每个静态的ExecutorService 创建方法都被重载为接受一个TreadFactory对象，而这个对象将被用来创作
 * 新的线程。
 *
 */
public class DaemonFromFactory implements Runnable {
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                print(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            print("Interrupted");
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool(
                new DaemonThreadFactory());
        for (int i = 0; i < 10; i++)
            exec.execute(new DaemonFromFactory());
        print("All daemons started");
        TimeUnit.MILLISECONDS.sleep(500); // Run for a while
    }
} /* (Execute to see output) *///:~
