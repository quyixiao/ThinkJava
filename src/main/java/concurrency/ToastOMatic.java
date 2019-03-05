package concurrency;//: concurrency/ToastOMatic.java
// A toaster that uses queues.

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;


/*****
 *  715页
 *      吐司BlockingQueue
 *      考虑下面这个使用BlockingQueue的示例，有一台机器具有三个任务，一个制作任务，一个制作吐司，一个
 *  给吐司抹黄油的吐司上涂果酱，我们可以通过和个处理过程之间的BlockingQueue来运行这个吐司的制作程序
 *      Toast是一个使用enum值的优秀示例，注意这个示例中没有任何显式的同步，即使用Lock对象或synchroinzed
 *  关键字，因为同步队列其内部是同步的，和系统的设计隐式的管理
 *  了，每片toast在任何时刻都只由一个任务在操作，因为队列的阻塞，使得处理过程将被自动的挂起
 *  和恢复，你可以，使得处理过程将被自动的挂起和恢复，你可以看到由BlockingQueue产生的简化的十分明显，在使用显式的wait()和
 *  notifyAll()时存在的类和类之间的耦合被消除，因为每个类都只和它的BlockingQueue通信。
 *
 *
 *
 *  1
 *
 *
 *
 *
 *      
 */
class Toast {
    public enum Status {DRY, BUTTERED, JAMMED}

    private Status status = Status.DRY;
    private final int id;

    public Toast(int idn) {
        id = idn;
    }

    public void butter() {
        status = Status.BUTTERED;
    }

    public void jam() {
        status = Status.JAMMED;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Toast " + id + ": " + status;
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {




}

class Toaster implements Runnable {
    private ToastQueue toastQueue;
    private int count = 0;
    private Random rand = new Random(47);

    public Toaster(ToastQueue tq) {
        toastQueue = tq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(
                        100 + rand.nextInt(500));
                // Make toast
                Toast t = new Toast(count++);
                print(t);
                // Insert into queue
                toastQueue.put(t);
            }
        } catch (InterruptedException e) {
            print("Toaster interrupted");
        }
        print("Toaster off");
    }
}

// Apply butter to toast:
class Butterer implements Runnable {
    private ToastQueue dryQueue, butteredQueue;

    public Butterer(ToastQueue dry, ToastQueue buttered) {
        dryQueue = dry;
        butteredQueue = buttered;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = dryQueue.take();
                t.butter();
                print(t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            print("Butterer interrupted");
        }
        print("Butterer off");
    }
}

// Apply jam to buttered toast:
class Jammer implements Runnable {
    private ToastQueue butteredQueue, finishedQueue;

    public Jammer(ToastQueue buttered, ToastQueue finished) {
        butteredQueue = buttered;
        finishedQueue = finished;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = butteredQueue.take();
                t.jam();
                print(t);
                finishedQueue.put(t);
            }
        } catch (InterruptedException e) {
            print("Jammer interrupted");
        }
        print("Jammer off");
    }
}

// Consume the toast:
class Eater implements Runnable {
    private ToastQueue finishedQueue;
    private int counter = 0;

    public Eater(ToastQueue finished) {
        finishedQueue = finished;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = finishedQueue.take();
                // Verify that the toast is coming in order,
                // and that all pieces are getting jammed:
                if (t.getId() != counter++ ||
                        t.getStatus() != Toast.Status.JAMMED) {
                    print(">>>> Error: " + t);
                    System.exit(1);
                } else
                    print("Chomp! " + t);
            }
        } catch (InterruptedException e) {
            print("Eater interrupted");
        }
        print("Eater off");
    }
}

public class ToastOMatic {
    public static void main(String[] args) throws Exception {
        ToastQueue dryQueue = new ToastQueue(),
                butteredQueue = new ToastQueue(),
                finishedQueue = new ToastQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue, butteredQueue));
        exec.execute(new Jammer(butteredQueue, finishedQueue));
        exec.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
} /* (Execute to see output) *///:~
