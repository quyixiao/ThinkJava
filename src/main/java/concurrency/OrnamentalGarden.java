package concurrency;//: concurrency/OrnamentalGarden.java

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;


/******
 *
 * 691页
 * 终结任务
 *      在前面的某些示例中，cancel()和isCanceed()方法被放到了一个所有的任务都可以看到的类中。
 *  这些任务通达检查isCanceled()来确定何时终止它们自己，对于这个问题来说，这是一种合理的
 *  方式，但是，在某些情况下，任务必须更加突然的终止，本节你将学习到有关的这种终止的各类的
 *  话题的问题。
 *  装饰性花园
 *      在这个仿真程序中，花园委员希望了了解每天通过多个在门进入公园的总人数，每个大门
 *  都有一个十字转门或某种其他的形式的计数器，并且任何一个十字转门的计数值递增时，就表示
 *  公园总人数的共享计数什也会递增
 *      这里使用单个的Count对象来跟踪花园参观者的主计数器，并且将其当作Entracnce类中的一
 *  个静态域进行存储，count.increment()和Count.value()都是synchroized的，用来控制对Count
 *  域的访问，increment()方法使用了Random对象，目的是从把count读取到temp中，到递增
 *  temp并将其存储回count的这段时间里，有大约一半的时间产生让步，如果你将increment()上的
 *  synchronized关键字注释掉，那么这个程序就会崩溃，因为多个任务将同时访问并修改count
 *  (yield()会使问题更快地发生)
 *      每个Entrance任务都维护着一个本地值number,它包含通过某个特定入口进入的参观都的数量。
 *  这提供了对count对象的双重检查，以确保其记录的参观者数量的正确的，Entrance.run()只是递增
 *  number和count对象，然后休眠100毫秒
 *      这个程序在以稳定的方式关闭所有的事物方面还有一些小麻烦，其部分原因是为了说明在终止
 *  多线程程序时你必须相当小心，而另一个部分的原因是为了演示interrupt()的值，稍后你学习
 *  有关这个值的知识。
 *      在3秒钟以后，main()向entrance发送static cancel()消息，然后调用exec对象的shutdown()方法
 *  ，之后调用exec上的awaitTermination()方法，ExecutorService.awaitTermination()等待每个任务
 *  结束，如果所有的任务在超时时间达到之前全部结束，则返回true,否则返回false,还未不是所有的任务都
 *  己经结束了，尽管这会收藏到每个任务都退出其run()方法，并因此作为任务而终止，
 *  但是Entreach对象仍然是有效的，尽管这会导致每个任务都退出其run()方法，并因此作为任务而终止
 *  但是Entrance对象仍旧是有效的，因此在构造器中，每个Entrance对象都存储在称为entrances的
 *  静态List<Entrance>中，因此，sumEntrances()仍旧可以作用于这些有效的Entrance对象。
 *      当这个程序运行时，你将会看到，在人们通过十字转门时，将显示总人数和通过每个入口的
 *  人数，如果移除count.increment()上面的synchronized声明，你将会注意到总人数和你的期望值有差异。
 *  每个十字转门统计的人数将与count的值不同，只要用互斥的同步来对count的访问，问题就可以解决
 *  请记住，Count.increment()通过使用temp和yidld()，增加了失败的可能性，
 *  在真正的线程问题上中，失败的可能性从统计学的角度来看可能性非常小，因此你可能很容易就掉进了
 *  轻信所有事物都将正确工作的陷阱里，就像在上面的示例中，有些还未发生了问题就有可能会
 *  隐藏起来，因此在复审并发代码时，要格外上小心。
 *
 *
 *
 * 1
 *
 */
class Count {
    private int count = 0;
    private Random rand = new Random(47);

    // Remove the synchronized keyword to see counting fail:
    public synchronized int increment() {
        int temp = count;
        if (rand.nextBoolean()) // Yield half the time
            Thread.yield();
        return (count = ++temp);
    }

    public synchronized int value() {
        return count;
    }
}

class Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<Entrance>();
    private int number = 0;
    // Doesn't need synchronization to read:
    private final int id;
    private static volatile boolean canceled = false;

    // Atomic operation on a volatile field:
    public static void cancel() {
        canceled = true;
    }

    public Entrance(int id) {
        this.id = id;

        // Keep this task in a list. Also prevents
        // garbage collection of dead tasks:
        entrances.add(this);
    }

    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            print(this + " Total: " + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                print("sleep interrupted");
            }
        }
        print("Stopping " + this);
    }

    public synchronized int getValue() {
        return number;
    }

    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance entrance : entrances)
            sum += entrance.getValue();
        return sum;
    }
}

public class OrnamentalGarden {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Entrance(i));
        // Run for a while, then stop and collect the data:
        TimeUnit.SECONDS.sleep(3);
        Entrance.cancel();
        exec.shutdown();
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
            print("Some tasks were not terminated!");
        print("Total: " + Entrance.getTotalCount());
        print("Sum of Entrances: " + Entrance.sumEntrances());
    }
}




/* Output: (Sample)
Entrance 0: 1 Total: 1
Entrance 2: 1 Total: 3
Entrance 1: 1 Total: 2
Entrance 4: 1 Total: 5
Entrance 3: 1 Total: 4
Entrance 2: 2 Total: 6
Entrance 4: 2 Total: 7
Entrance 0: 2 Total: 8
...
Entrance 3: 29 Total: 143
Entrance 0: 29 Total: 144
Entrance 4: 29 Total: 145
Entrance 2: 30 Total: 147
Entrance 1: 30 Total: 146
Entrance 0: 30 Total: 149
Entrance 3: 30 Total: 148
Entrance 4: 30 Total: 150
Stopping Entrance 2: 30
Stopping Entrance 1: 30
Stopping Entrance 0: 30
Stopping Entrance 3: 30
Stopping Entrance 4: 30
Total: 150
Sum of Entrances: 150
*///:~
