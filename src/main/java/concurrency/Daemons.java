package concurrency;//: concurrency/Daemons.java
// Daemon threads spawn other daemon threads.

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.printnb;


/****
 *
 *
 *
 * 664
 *
 *
 *
 *
 * daemon线程被设置成了后台模式，然后派生出了许多的子线程，这些线程并没有被显式的设置为后台模式，不过它们的确是
 * 后台的线程，接着，Daemon线程进入了无限循环，并在循环里调用yield()方法把控制权交给其他的进程。
 *
 *      你应该意识到后台进程在不执行finaly出子句的情况下会终止其run()方法。
 *      当你运行这个程序时，你看到finally子句就不会执行，但是如果你注释掉setDaemon()的调用，就会
 *      看到finally子句将会执行。
 *      这种行为是正确的，即便你基于前面对finally给出的承诺，并不希望这种行为，但情况仍将如此，当最后一个非后台线程
 * 终止时，后台线程将会突然 终止，因此一旦main退出，JVM将会立即关闭所有的后台线程，所以它们几乎不是一种好的思想，非后台的Executor通常
 * 是一种更好的方式，因为Executor控制的所有的任务可以同时被关闭，正如你将要在本章稍后看到的，在这种情况下，关闭将以有序的方式执行
 *                                                         O
 *
 *
 * void main(){
 *     test(){
 *         // 是这样的一个人，我觉得还是比较好的，因这是是一个
 *         void main(){
 *
 *             void main(){
 *                 
 *             }
 *         }
 *     }
 * }
 *
 *
 *
 *
 *  前台小伙伴，是这样的一个他是这样的，因为这是一中国人民是一个不估量的测试
 *
 *
 *
 *
 *  
 * 1
 *
 */
class Daemon implements Runnable {
    private Thread[] t = new Thread[10];

    public void run() {
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            printnb("DaemonSpawn " + i + " started, ");
        }
        for (int i = 0; i < t.length; i++)
            printnb("t[" + i + "].isDaemon() = " +
                    t[i].isDaemon() + ", ");
        while (true)
            Thread.yield();
    }
}



class DaemonSpawn implements Runnable {
    public void run() {
        while (true)
            Thread.yield();
    }
}

public class Daemons {
    public static void main(String[] args) throws Exception {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        printnb("d.isDaemon() = " + d.isDaemon() + ", ");
        // Allow the daemon threads to
        // finish their startup processes:
        TimeUnit.SECONDS.sleep(1);
    }
} /* Output: (Sample)
d.isDaemon() = true, DaemonSpawn 0 started, DaemonSpawn 1 started, DaemonSpawn 2 started, DaemonSpawn 3 started, DaemonSpawn 4 started, DaemonSpawn 5 started, DaemonSpawn 6 started, DaemonSpawn 7 started, DaemonSpawn 8 started, DaemonSpawn 9 started, t[0].isDaemon() = true, t[1].isDaemon() = true, t[2].isDaemon() = true, t[3].isDaemon() = true, t[4].isDaemon() = true, t[5].isDaemon() = true, t[6].isDaemon() = true, t[7].isDaemon() = true, t[8].isDaemon() = true, t[9].isDaemon() = true,
*///:~
