package concurrency;//: concurrency/SimpleDaemons.java
// Daemon threads don't prevent the program from ending.

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;


/*****
 *      必须在线程启动之前调用setDaemon()方法，才能把它设置为后台线程。
 *      一旦main()完成其他的工作，就没有什么能阻止程序终止了，因为除了最后线程之处，已经没有线程在运行了，
 * 所谓后台线程是指在程序运行的时候后台提供一种通用的服务的线程，并且这种线程并不属于程序中不可或缺的部分。
 * 因此，当所有的非后台线程结束时，程序也就终止了，同时会杀死进程中的所有的后台线程，反过来说，只要有任何
 * 非后台线程还在运行，程序就不会终止，比如，执行main()的就是一个非后台的线程。
 *
 *      一旦main()完成其工作，就没什么能阻止程序终止了，因为个除了后台线程之外，己经没有线程在运行了，
 * main()线程被设定为暂睡眠了，所以可以观察到所有后台线程启动后的结果，不这样的话，你就只能看见一些后台
 * 线程创建得到的结果（试试调整sleep()休眠时间以观察这个行为）
 *      SimpleDaemons.java创建了显式的线程，以便可以设置它们后台的标志，通过编写定制的threadFactory可
 * 定制的由Executor创建的属性，后台，优先级，名称。
 *
 */
public class SimpleDaemons implements Runnable {
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                print(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            print("sleep() interrupted");
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true); // Must call before start()
            daemon.start();
        }
        print("All daemons started");
        TimeUnit.MILLISECONDS.sleep(175);
    }
} /* Output: (Sample)
All daemons started
Thread[Thread-0,5,main] SimpleDaemons@530daa
Thread[Thread-1,5,main] SimpleDaemons@a62fc3
Thread[Thread-2,5,main] SimpleDaemons@89ae9e
Thread[Thread-3,5,main] SimpleDaemons@1270b73
Thread[Thread-4,5,main] SimpleDaemons@60aeb0
Thread[Thread-5,5,main] SimpleDaemons@16caf43
Thread[Thread-6,5,main] SimpleDaemons@66848c
Thread[Thread-7,5,main] SimpleDaemons@8813f2
Thread[Thread-8,5,main] SimpleDaemons@1d58aae
Thread[Thread-9,5,main] SimpleDaemons@83cc67
...
*///:~
