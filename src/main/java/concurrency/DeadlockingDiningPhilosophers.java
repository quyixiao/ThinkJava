package concurrency;//: concurrency/DeadlockingDiningPhilosophers.java
// Demonstrates how deadlock can be hidden in a program.
// {Args: 0 5 timeout}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/*****
 *
 *      721页
 *      在Philosopher，run()中，每个Philosopher只是不断地思考和吃饭，如果PonderFactor不为0，
 * 则pause()方法会休眠（sleeps()）一段随机时间，通过使用这种方式，你将看到Philosopher会在思考
 * 上花掉一段随机化的时间，然后尝试获取（take()）右边和左边的Chopstick,随后在吃
 * 饭上再花掉一段随机化的时间，之后重复此过程。
 *      现在我们可以建立这个程序的将会产生死锁的版本了。
 *
 *      你会发现，如果Philosopher花在思考上的时间非常少，那么当他们想要进餐时，全都会在
 * Chopstick上产生竞争，而死锁就会更快的发生。
 *      第一个命令行参数可以调整ponder因子，从而影响每个philosopher花费在思考上的时间长
 * 度，如果有许多Philosopher，或者他们花费很多时间去思考，那么尽管存在死锁可能，但
 * 你可能永远也看不到死锁，值为0的命令行参数倾向于使锁尽快发生
 *      注意：Chopsitick对象不需要内部标识等符，它们是由在数组sticks中的位置来标识的，每个
 * Philosopher构造器都会得到一个左边和右边的Chosptick对象的引用，除了最后一个Philosopher,
 *其他所有的Philosopher者是通过将这个Phiolosopher定位于下一对Chopsitick对象之间而被初始化
 * 的，而最后一个Philsopher右边的Chostick是第0个Chopstick，这样的这个循环表也就结束了，
 * 因为最后一个Philopher都有可能希望进餐，从而等待其临近的Philosopher放下它们的Chopstick这
 * 将全程序死锁
 *      如果Philosopher花费事多的时间去思考而不是进餐，使用非0的ponder值，或者大量的
 * Philosopher),那么它们非如此，这个示例相当的有趣，因为它演示了看起来可以正确的运行，但实际上
 * 会死锁程序。
 *      要修正死锁问题，你必须明白，当以下四个条件同时满足时，就会发生死锁
 *      1）互斥条件，任务使用的资源中至少有一个资源不能共享的，这里一根Chopstick一次就只能被一个
 *  Philosopher使用。
 *      2）至少有一个任务这必须持有一个资源且正在等待获取一个当前被识别的任务持有的资源。
 *  也就是说，要发生死锁，Philsopher必须拿着一根Chopstick并且等待另一根
 *      3）资源不能被任务抢占，任务必须 所资源释放当作普通事件，Philosopher很有礼貌，他们
 *  不会从其他的Philosopher那里抢Chostick
 *      4)必须有循环等待，这时，一个任务等待其他任务所持有的资源，后者以在等待另一个任务。
 *  所持有的资源，这样一直下去，直到有一个任务在等待第一个任务所持有的资源，使得大家都
 *  被锁住，在DeadLockingDingPhilsophers.java中，因为第一个Philosopher都试图先得到右边的
 *  Chopstick，然后得到左边的Chopstick，所以发生的循环等待。
 *      因为要发生死锁话，所有死锁最容易的方法全部满足，所以要防止死锁的话，只需要破坏其中一个即
 *  可，程序中，防止死锁最容易的方法是破坏第4个条件，有这个条件的原因是每个
 *  Philosopher都试图有特定的顺序拿Chopstick，先右后左，正因为如此，就可能会发生，每个人最后一个
 *  Philosophe被初始化成先拿左边的Chopstick，后全右边的Chositck,那么这个Philosopher将永远不会以阻止 其右边的
 *  Philosopher拿起他们的Chopstick，在配合中，这就可以防止循环等待，这只是问题的解决方法之一，也可
 *  以通过破坏其他条件来防止死锁，具体细节请勇者更高级的计分线程的书籍。
 *
 */
public class DeadlockingDiningPhilosophers {
    public static void main(String[] args) throws Exception {
        int ponder = 5;
        if (args.length > 0)
            ponder = Integer.parseInt(args[0]);
        int size = 5;
        if (args.length > 1)
            size = Integer.parseInt(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++)
            sticks[i] = new Chopstick();
        for (int i = 0; i < size; i++)
            exec.execute(new Philosopher(
                    sticks[i], sticks[(i + 1) % size], i, ponder));
        if (args.length == 3 && args[2].equals("timeout"))
            TimeUnit.SECONDS.sleep(5);
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
} /* (Execute to see output) *///:~
