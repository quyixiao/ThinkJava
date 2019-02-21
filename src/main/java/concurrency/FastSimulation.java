package concurrency;//: concurrency/FastSimulation.java

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static net.mindview.util.Print.print;


/*****
 *
 *
 * 760页
 *      尽管Atomic对象执行像deconretmetnAdnGet()这样的原子性操作，但是某个Atomic类还允许
 *  你执行据库的乐观加锁，这意味半当你执行蕾斯计算时，实际上没有使用互斥，但是在这基计算完成，并且你准备
 *  是新这个Atomic对象时，你需要使用一个称为compareAnndSet（）的方法，你将卓伟和次机会一起提交给这个
 *  方法，如果旧值与它在Atomic对象中发现的会不一致，那么这个操作就是失败的，这意味着某个其他的任务
 *      如果areAndSet()操作失败会发生什么，这正是棘手的地方，也是你在及应用这项技术时的受限之处
 *  即只能针对能够吻合这些需求的问题，如果compareAdnSet()失败，那么就必须决定那么就必须决定
 *  做些什么，这是一个非常重要的问题，因为如果不能执行某些恢复操作，那么你就不能使用这项技术，
 *  从而必须使用传统的互斥，你可能会重试这个操作，如果在第二次成功，那么万事大吉，或者可能
 *  会忽略这次失败，直接结束，在某些仿真中，如果数据点丢失，在重要的框架中，这就是
 *  最终需要做的事情，当然，你必须很好地理解 你的模型，以了解情况是不确实如此。
 *      考虑一个假想的仿真，它由长度为30的10000个基因构成，这可能是某种类型的中会算法的起源
 *  假设伴随着遗传算法的每次 进化 都会发生某些代价高昂的计算，因此你决定使用
 *  一多处理器来分布这些任务以提高性能，另外，你将使用Atomic对象而不是Lock对象来防止互斥开销，
 *  当然 一开始，你使用synchronized关键字以最简单的方编写代码，一旦你运行该程序，发现太慢了，并
 *  开始应用性能调优技术，而此时你也只能写出这样的解决方案，因为你的模型的特性，使得如果计算过程不
 *  发生冲突，那么发现冲突的任务直接的忽略他，并不会更新它的值，下面是这个示例代码的。
 *      所有的元素被置于数组内，这被认为提高性能，这个假设将在一个练习中进行测试，
 *   每个Evolover对象用它前一个元素和后一个元素来平均它的值，如果在更新时失败，那么将直接打印
 *   这个值并继续执行，注意，在这个程序中没有出现任何互斥。
 *
 *
 *
 *   1
 *
 *
 *
 *
 *
 */
public class FastSimulation {
    static final int N_ELEMENTS = 100000;
    static final int N_GENES = 30;
    static final int N_EVOLVERS = 50;
    static final AtomicInteger[][] GRID =
            new AtomicInteger[N_ELEMENTS][N_GENES];
    static Random rand = new Random(47);

    static class Evolver implements Runnable {
        public void run() {
            while (!Thread.interrupted()) {
                // Randomly select an element to work on:
                int element = rand.nextInt(N_ELEMENTS);
                for (int i = 0; i < N_GENES; i++) {
                    int previous = element - 1;
                    if (previous < 0) previous = N_ELEMENTS - 1;
                    int next = element + 1;
                    if (next >= N_ELEMENTS) next = 0;
                    int oldvalue = GRID[element][i].get();
                    // Perform some kind of modeling calculation:
                    int newvalue = oldvalue +
                            GRID[previous][i].get() + GRID[next][i].get();
                    newvalue /= 3; // Average the three values
                    if (!GRID[element][i]
                            .compareAndSet(oldvalue, newvalue)) {
                        // Policy here to deal with failure. Here, we
                        // just report it and ignore it; our model
                        // will eventually deal with it.
                        print("Old value changed from " + oldvalue);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < N_ELEMENTS; i++)
            for (int j = 0; j < N_GENES; j++)
                GRID[i][j] = new AtomicInteger(rand.nextInt(1000));
        for (int i = 0; i < N_EVOLVERS; i++)
            exec.execute(new Evolver());
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
} /* (Execute to see output) *///:~
