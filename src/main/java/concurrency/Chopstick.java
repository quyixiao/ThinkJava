package concurrency;//: concurrency/Chopstick.java
// Chopsticks for dining philosophers.


/*****
 * 719页
 *
 *
 *      任何两个Philosopher都不能成功take()同一根筷子，另外，如果一根chopstick己经被某个
 *  Philosopher获得，那么另一个Philosopher可以wait()，直到这根Chpstice的当前持有调用
 *  drop()使其可用为止。
 *
 *  现在可以理解了，一个对象可以有synchorized方法或其他形式的加锁机制来防止另的任务在
 *  互斥还没有eoyt的时候就访问这个对象，你已经学习过，任务可以变成阻塞状态，所以就可能出现
 *  这种情况，某个任务在，而后都又等特别的任务，这样一直下去，直到这个链条上的任务又在等待第一个
 *  任务释放锁，这得到一个任务之间的相系微博微信的连续循环，没有
 *  哪个线程能继续，这被称之为死锁
 *
 *
 *
 *
 *
 *
 *
 *
 * 1
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
public class Chopstick {
    private boolean taken = false;

    public synchronized void take() throws InterruptedException {
        while (taken)
            wait();
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
} ///:~
