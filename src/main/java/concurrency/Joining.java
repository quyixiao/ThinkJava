package concurrency;//: concurrency/Joining.java
// Understanding join().

import static net.mindview.util.Print.print;


/****
 *
 *
 * 670 页
 * 加入一个线程
 *  一个线程可以在其他的酷上调用join()方法，其效果是等待一段时间直到第二个线程结束，才能继续执行，如果某个线程在另一个
 *  线程t上调用t.join()，此线程将被挂起，直到上档线程结束才恢复，即t.isAlive()返回假
 *      也可以在调用join()时带上一个超时参数，单位可以是毫秒，或者和纳秒，这样如果上档线程在这段时间到期时还没有结束时
 *      的话，join()方法总能返回。
 *       对join()方法的调用可能被中断，做法是在调用线程上调用interrupt()方法，这时需要用到try-catch()子句
 *
 *      Sleeper是一个Thread类型，它要休眠一段时间，这段时间是骑过构造器传进来的参数所指定的，在run()中，sleep()方法是有可能
 *      在指定的时间其满时返回，但也可能被中断，在catch子名中，将根据isInterupted()的返回值报告这个中断，当另一个线程在该线程
 *      上调用interupt()时，将给该线程设定一个标志，表明该线程已经被中断，然而，异常被捕获时，将清理这个标志，所以他在catch子句
 *      中，在异常被捕获的时候这个标志总是为假，除异常之外，这个标志还可用于其他的情况，比如线程可能会检查其中断状态。
 *
 *         Joinner线程将通过在Slepper对象上调用join()方法来等待Sleepr醒来，在main()里面，每个Sleepr都有一个Joiner，这可以
 *         在输出中发现，如果Sleepr被中断或者是正常结束，Joiner将和Slpper一同结束。
 *         注意，Java SE 5 的java.unit.concurrent类库包含诸如CyclicBarrier本章稍后会展示，这样的工具，它们可能比最初的
 *        线程类库中的join()更加适合。
 *
 *        如前所述，俩月和线程的动机之一就是建立有响应的用户界面，尽管我们要到22章才接触到图形用户界面，但下面还是线出了一个其于
 *        控制台用户界面的简单教学示例，下面的例子有两个版本，一个关注于运算，所以不能读取控制台输入，另一个把运算放在任务时单独
 *        运行，此时就可以在进行运算的同时监听控制台输入。
 *
 *
 *
 * 测试数据迁移，我们测试的有道字典，是这样的哈，因为我觉得还是比较好的，因为这样的一个人
 * 注意Java SE5的java.util.conconrrent类库包含诸如CyclicBarrier，本章稍后会展示，
 *  这样的工具，它们可能比最初的线程类库的join()更加适合
 *  测试有道，字典，是这样，因为，我觉得还是比较好的，因为，他是这样的哈。
 *  个人觉得还是比较好的
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }

    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            print(getName() + " was interrupted. " +
                    "isInterrupted(): " + isInterrupted());
            return;
        }
        print(getName() + " has awakened");
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            print("Interrupted");
        }
        print(getName() + " join completed");
    }
}

public class Joining {
    public static void main(String[] args) {
        Sleeper
                sleepy = new Sleeper("Sleepy", 1500),
                grumpy = new Sleeper("Grumpy", 1500);
        Joiner
                dopey = new Joiner("Dopey", sleepy),
                doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }
} /* Output:
Grumpy was interrupted. isInterrupted(): false
Doc join completed
Sleepy has awakened
Dopey join completed
*///:~
