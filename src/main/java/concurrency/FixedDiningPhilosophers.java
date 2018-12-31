package concurrency;//: concurrency/FixedDiningPhilosophers.java
// Dining philosophers without deadlock.
// {Args: 5 5 timeout}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/*****
 * 722页
 *      通过确保最后一个Philosopher先拿起和放下左边的Chopstick，我们可以移除死锁，从而使
 *  这个程序平滑地运行。
 *      Java对死锁并没有提供语言层面的支持，能否通过仔细地设计程序来避免死锁，这取决于你自己，对于正在试图
 *  调试一个有死锁的程序的程序员来说，这不是什么安慰人的话。
 *  新类库中的构件
 *      java SE5的java.util.concurrent引入了大量的设计用来解决并发问题的新类。学习使用它们将有助于你
 *  编写出更加简单而健壮的竞争性程序。
 *      本节包含了各种组件具有性的示例，但是少数组件，即那些你不太可能用到或碰到
 *  的组件，没有包括在内。
 *      因为这些组件设计各种问题，所以没有一种清晰的方式可以用来组织它们，因此我尝试着
 *  从最简单的示例入手，逐渐增加复杂度，从而介绍所有的示例。
 *
 *
 *
 *
 */
public class FixedDiningPhilosophers {
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
            if (i < (size - 1))
                exec.execute(new Philosopher(
                        sticks[i], sticks[i + 1], i, ponder));
            else
                exec.execute(new Philosopher(
                        sticks[0], sticks[i], i, ponder));
        if (args.length == 3 && args[2].equals("timeout"))
            TimeUnit.SECONDS.sleep(5);
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
} /* (Execute to see output) *///:~
