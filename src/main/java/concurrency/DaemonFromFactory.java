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
 *  这与普通的ThreadFactory的唯一的差异就是它将后台状态全部设置为了true,你现在可以调用一个
 *  新的DaemonThreadFactory作为参数传递给Executor.newCachedThreadPool()
 *
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
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i = 0; i < 10; i++) {
            exec.execute(new DaemonFromFactory());
        }
        print("All daemons started");
        TimeUnit.MILLISECONDS.sleep(500); // Run for a while
    }
} /* (Execute to see output) *///:~
