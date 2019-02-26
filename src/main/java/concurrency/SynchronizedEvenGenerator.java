package concurrency;//: concurrency/SynchronizedEvenGenerator.java
// Simplifying mutexes with the synchronized keyword.
// {RunByHand}


/******
 *  对Thread.yield()的调用被插入到了两个递增操作之间，以提高在currentEventValue是奇数
 *  状态的可能性，因为互斥可以防止多个任务同时进入临界区，所以这不会产生任何失败，
 *  但是如果失败将会发生，调用yield()是一种促使其发生的有效方式
 *
 *
 *
 *  第一个进入next()的任务将获得锁，任何时刻只有一个任务可以通过由互斥量看护的代码
 *
 *
 *
 *
 * 1
 *
 *
 *
 */
public class
SynchronizedEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield(); // Cause failure faster
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
} ///:~
