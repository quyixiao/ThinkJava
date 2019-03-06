package concurrency;//: concurrency/PriorityBlockingQueueDemo.java

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/******
 *  728页
 *
 *
 *
 *      这是一个很基础的优先级队列，它具有可阻塞的读取操作，下面的一个示例，其中的优先级队列中
 * 的对象是按照优先级顺序从队列中出现的任务，PrioritizedTask被赋予了一个优先级数字 ，
 *  以此来提供这种顺序。
 *      与前一个示例相同，PrioritizedTask对象的创建序列被记录在sequence List 中，用于和实际
 *  的执行顺序比较，run()方法将一段随机时间，然后打印对象信息，而endSentimel提供了
 *  前面的相同的功能，要确保它是队列中最后一个对象。
 *      PrioritizedTaskProducer和PrioritizedTaskConsumer通过PrioritylockinQueue彼此连接
 *  因为这种队列的阻塞特性提供了所有必需同步，所以你应该注意到了，这里不需要任何显式的
 *  同步，不必考虑当你从这种队列中读取时，其中是否有元素，因为这个队列在没有元素时，
 *  将直接阻塞读取者。
 *
 *
 *
 *
 *  1
 *
 */
class PrioritizedTask implements  Runnable, Comparable<PrioritizedTask> {
    private Random rand = new Random(47);
    private static int counter = 0;
    private final int id = counter++;
    private final int priority;
    protected static List<PrioritizedTask> sequence =
            new ArrayList<PrioritizedTask>();

    public PrioritizedTask(int priority) {
        this.priority = priority;
        sequence.add(this);
    }

    public int compareTo(PrioritizedTask arg) {
        return priority < arg.priority ? 1 :
                (priority > arg.priority ? -1 : 0);
    }

    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
        } catch (InterruptedException e) {
            // Acceptable way to exit
        }
        print(this);
    }

    public String toString() {
        return String.format("[%1$-3d]", priority) +
                " Task " + id;
    }

    public String summary() {
        return "(" + id + ":" + priority + ")";
    }

    public static class EndSentinel extends PrioritizedTask {
        private ExecutorService exec;

        public EndSentinel(ExecutorService e) {
            super(-1); // Lowest priority in this program
            exec = e;
        }

        public void run() {
            int count = 0;
            for (PrioritizedTask pt : sequence) {
                printnb(pt.summary());
                if (++count % 5 == 0)
                    print();
            }
            print();
            print(this + " Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

class PrioritizedTaskProducer implements Runnable {
    private Random rand = new Random(47);
    private Queue<Runnable> queue;
    private ExecutorService exec;

    public PrioritizedTaskProducer(
            Queue<Runnable> q, ExecutorService e) {
        queue = q;
        exec = e; // Used for EndSentinel
    }

    public void run() {
        // Unbounded queue; never blocks.
        // Fill it up fast with random priorities:
        for (int i = 0; i < 20; i++) {
            queue.add(new PrioritizedTask(rand.nextInt(10)));
            Thread.yield();
        }
        // Trickle in highest-priority jobs:
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTask(10));
            }
            // Add jobs, lowest priority first:
            for (int i = 0; i < 10; i++)
                queue.add(new PrioritizedTask(i));
            // A sentinel to stop all the tasks:
            queue.add(new PrioritizedTask.EndSentinel(exec));
        } catch (InterruptedException e) {
            // Acceptable way to exit
        }
        print("Finished PrioritizedTaskProducer");
    }
}

class PrioritizedTaskConsumer implements Runnable {
    private PriorityBlockingQueue<Runnable> q;

    public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
        this.q = q;
    }

    public void run() {
        try {
            while (!Thread.interrupted())
                // Use current thread to run the task:
                q.take().run();
        } catch (InterruptedException e) {
            // Acceptable way to exit
        }
        print("Finished PrioritizedTaskConsumer");
    }
}

public class PriorityBlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        Random rand = new Random(47);

        ExecutorService exec = Executors.newCachedThreadPool();

        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();



        exec.execute(new PrioritizedTaskProducer(queue, exec));


        exec.execute(new PrioritizedTaskConsumer(queue));
    }
} /* (Execute to see output) *///:~
