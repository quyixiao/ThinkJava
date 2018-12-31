package concurrency;//: concurrency/Chopstick.java
// Chopsticks for dining philosophers.


/*****
 * 719页
 *      任何两个Philosopher都不能成功take()同一根筷子，另外，如果一根chopstick己经被某个
 *  Philosopher获得，那么另一个Philosopher可以wait()，直到这根Chpstice的当前持有调用
 *  drop()使其可用为止。
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
