package concurrency;//: concurrency/MultiLock.java
// One thread can reacquire the same lock.

import static net.mindview.util.Print.print;


/*****
 *  699页
 *      在main中创建了一个调用f1()的Thread,然后f1()f2()互相调用直至count变为0，由于这个任务
 *  己经在第一个对f1()调用中获得了multiLock对象锁，因此同一个任务将在对f2()的调用
 *  中再次获取这个锁，依此类推，这么是做有意义的，因为一个任务应该能够调用在同一个对象
 *  中的其他的synchronized方法，而这个任务己经持有了锁了
 *      就像前面在不可中断的I/O中所观察到的那样，无论在任务时刻，只要任务以不可中断的方式被
 *  阻塞，那么都有潜在的会锁住程序的可能，Java SE5并发类库中添加了一个特性，即在ReentrantLock
 *  上阻塞的任务具备可以被中断的能力，这与在synchronized方法或临界区阻塞的任务完全不同。
 *
 *
 *
 *
 *  1
 *
 */
public class MultiLock {
    public synchronized void f1(int count) {
        if (count-- > 0) {
            print("f1() calling f2() with count " + count);
            f2(count);
        }
    }

    public synchronized void f2(int count) {
        if (count-- > 0) {
            print("f2() calling f1() with count " + count);
            f1(count);
        }
    }

    public static void main(String[] args) throws Exception {
        final MultiLock multiLock = new MultiLock();
        new Thread() {
            public void run() {
                multiLock.f1(10);
            }
        }.start();
    }
}







/* Output:
f1() calling f2() with count 9
f2() calling f1() with count 8
f1() calling f2() with count 7
f2() calling f1() with count 6
f1() calling f2() with count 5
f2() calling f1() with count 4
f1() calling f2() with count 3
f2() calling f1() with count 2
f1() calling f2() with count 1
f2() calling f1() with count 0
*///:~
