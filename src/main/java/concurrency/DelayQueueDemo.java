package concurrency;//: concurrency/DelayQueueDemo.java

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/*****
 *  726 页
 *
 *
 *
 *      这是一个无界的BlockingQueue，用于放置实现了Delayed接口对象的，其中的对象只能在其到期才能
 *  从队列中取走，这种队列是有序的，即从队头对象到延迟到期的时间最长，如果没有
 *  任何延迟到期，那么就不会有任何头元素，并且poll（）将返回null(正因为这样，你不能将null放置到)
 *  种队列中。
 *      下面是一个示例，其中的Delelayed对象自身就是任务，而DelayedTaskConsumer将最 紧急
 *  的任务（到期时间最长的任务）从队列上取出，然后运行他，注意，这样 DelayQueue就成为了
 *  优先级队列的和种变体
 *      DelayedTask包含一个称为sequence的List<DelayedTask>,它保存了任务创建的顺序，因
 *  此我们可以看到排序是按照实际发生的顺序执行的。
 *      Delayed接口有一个方法名为getDelay(),它可以有来告知延迟到期有多长时间，或者延迟在
 *  多长时间之前己经到期，这个方法将强制我们去使用TimeUnit类，因为这就是参数类型，这会
 *  产生一个非常方便的类，因为你可以很容易地转换单位而无需做任何声明，例如，Delta的会是
 *  以毫秒为单位存储的，但是，java Se5 的方法Ssytem.nanotime(）首先的时间则是以纳秒为单位的，
 *  你可以转换delta的值，方法是声明它的单位以及你希望以什么单位来表示，就你下面这样。
 *      NanoSECONDS.conver(delta,MILLISECONDS);
 *      在getDelay()中，有希望使用的单位是作为unit参数传递进来的，你使用它将当前时间与触发
 *  时间之间的差转换为调用者要求的单位，而无需知道这些单位是什么，（这是策略设计模式的一
 *  个简单求示例），在这种模式中，算法的一部分是作为参数传递进来的。
 *      为了排序，Delayed接口还继承了Comparable接口，因此必须实现compareTo()，使其可以产生合理的
 *  的比较，toString()和summary()提供了输出格式化，而嵌套的EndSentinel类提供了一种关闭
 *  所有的事物的途径，具体做法是将其放置为队列的最后的一个元素。
 *      注意，因为DelayedTaskConsumer自身是一个任务，所以它有自己的Thread，它可以使用
 *  这个线程运行从队列中获取的所有任务。由于任务是按照从队列优先级顺序执行的，因此在
 *  本例中不需要启动任何单独的线程来运行DelayedTask
 *      从输出中可以看到，任务创建的顺序对执行顺序没有任何影响，任务是按照所期望的延迟
 *   顺序执行的
 *
 *
 *
 * 1
 *
 *
 *
 */
class DelayedTask implements Runnable, Delayed {
    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;
    protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

    public DelayedTask(int delayInMilliseconds) {
        delta = delayInMilliseconds;
        trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
        sequence.add(this);
    }

    public long getDelay(TimeUnit unit) {
        return unit.convert(
                trigger - System.nanoTime(), NANOSECONDS);
    }

    public int compareTo(Delayed arg) {
        DelayedTask that = (DelayedTask) arg;
        if (trigger < that.trigger) return -1;
        if (trigger > that.trigger) return 1;
        return 0;
    }

    public void run() {
        printnb(this + " ");
    }

    public String toString() {
        return String.format("[%1$-4d]", delta) +
                " Task " + id;
    }

    public String summary() {
        return "(" + id + ":" + delta + ")";
    }

    public static class EndSentinel extends DelayedTask {
        private ExecutorService exec;

        public EndSentinel(int delay, ExecutorService e) {
            super(delay);
            exec = e;
        }

        public void run() {

            print();
            for (DelayedTask pt : sequence) {
                printnb(pt.summary() + " ");
            }
            print();
            print(this + " Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

class DelayedTaskConsumer implements Runnable {
    private DelayQueue<DelayedTask> q;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
        this.q = q;
    }

    public void run() {
        try {
            while (!Thread.interrupted())
                q.take().run(); // Run task with the current thread
        } catch (InterruptedException e) {
            // Acceptable way to exit
        }
        print("Finished DelayedTaskConsumer");
    }
}

public class DelayQueueDemo {
    public static void main(String[] args) {
        Random rand = new Random(47);


        ExecutorService exec = Executors.newCachedThreadPool();


        DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();



        // Fill with tasks that have random delays:
        for (int i = 0; i < 20; i++) {
            int a = rand.nextInt(5000);
            System.out.println("========================a========="+(i )+"=========================" + a );
            queue.put(new DelayedTask(a));
        }



        // Set the stopping point
        queue.add(new DelayedTask.EndSentinel(5000, exec));
        exec.execute(new DelayedTaskConsumer(queue));




    }
}






/* Output:
[128 ] Task 11 [200 ] Task 7 [429 ] Task 5 [520 ] Task 18 [555 ] Task 1 [961 ] Task 4 [998 ] Task 16 [1207] Task 9 [1693] Task 2 [1809] Task 14 [1861] Task 3 [2278] Task 15 [3288] Task 10 [3551] Task 12 [4258] Task 0 [4258] Task 19 [4522] Task 8 [4589] Task 13 [4861] Task 17 [4868] Task 6 (0:4258) (1:555) (2:1693) (3:1861) (4:961) (5:429) (6:4868) (7:200) (8:4522) (9:1207) (10:3288) (11:128) (12:3551) (13:4589) (14:1809) (15:2278) (16:998) (17:4861) (18:520) (19:4258) (20:5000)
[5000] Task 20 Calling shutdownNow()
Finished DelayedTaskConsumer
*///:~
