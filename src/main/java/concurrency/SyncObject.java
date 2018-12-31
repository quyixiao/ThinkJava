package concurrency;//: concurrency/SyncObject.java
// Synchronizing on another object.

import static net.mindview.util.Print.print;


/****
 * 689页
 *      synchronized块必须给定一个在其上进行同步的对象，并且最合理的方式是，使用其方法正
 *  在被调用的对象：synchronized(this)，这正是PaireManager2所使用的方式，在这种方式中
 *  如果获得了synchronized块上的锁，那么该对象其他的synchroinzed方法和临界就不能被调用
 *  了，因此，如果在this上同步，临界区的效果就会直接缩小在同步的范围内。
 *      有时必须在另一个对象上同步，但是如果这么做，就必须确保所有的相关的任务在都是在
 *  同一个对象上同步，下面示例就演示了两个任务可以同时进入同一个对象，只要这个对象上的
 *  方法是在一同的锁上同步的即可。
 *      dualSync.f()（通过同步整个方法）在this同步，而g()有一个在sysncObject上同步的
 *  synchronized块，因此，这两个同步是互相独立的，通过在main()中创建调用f()的Thread对这一
 *  点进行演示，因为main()线程是被用来调用g()的，从输出中可以看到，这两个方法在同时运行，
 *  因此任何一个方法都没有因为另一个方法的同步而被阻塞。
 *
 */
class DualSynch {
    private Object syncObject = new Object();

    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            print("f()");
            Thread.yield();
        }
    }

    public void g() {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                print("g()");
                Thread.yield();
            }
        }
    }
}

public class SyncObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread() {
            public void run() {
                ds.f();
            }
        }.start();
        ds.g();
    }
} /* Output: (Sample)
g()
f()
g()
f()
g()
f()
g()
f()
g()
f()
*///:~
