//: concurrency/waxomatic2/WaxOMatic2.java
// Using Lock and Condition objects.
package concurrency.waxomatic2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/****
 * 711页
 *  使用显式的Lock和condition对象
 *      在java SE5 的java.util.concurrent类库中还有额外的显式的工具可以用来重写
 *  WaxOMatic.java，使用互斥并允许任务挂起的基本类是Condition，你可以通过在Condition上
 *  调用await()来挂起一个任务，从外部条件发生变化，意味着某个任务应该继续执行时，你可以
 *  通过调用signal()来通知这个任务，从而唤醒一个任务，或者调用signalAll()来唤醒所有在这个
 *  confition上被其自身挂起的任务，与使用notifyAll()相比，signalAll()是更安全的方式
 *      下面是WaxOMatic.java的重写版本，它包含一个Condition，用来在waitForWaxing()或
 *  waitForBufferig()内部挂起一个任务：
 *      在Car的构造器中，单个的Lock将产生一个Condition对象，这个对象被用来管理任务间的
 *  通信，但是，这个Condition对象不包含任何有关的处理状态的信息，即boolean WaxOn.
 *      每个对lock()调用者必须紧跟一个try-finally子句，用来保证在所有的情况下都可以释放锁
 *  在使用内建版本时，任务在可以调用wait(),gignal()或signalAll()之前，必须拥有这个锁。
 *      注意，这个解决方案比前一个更加复杂，在本例中这种复杂的性并未使你收获更多，Lock和
 *  Condition对象只有在更加困难的多线程问题中才是必需的。
 *
 *
 */
class Car {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean waxOn = false;

    public void waxed() {
        lock.lock();
        try {
            waxOn = true; // Ready to buff
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void buffed() {
        lock.lock();
        try {
            waxOn = false; // Ready for another coat of wax
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void waitForWaxing() throws InterruptedException {
        lock.lock();
        try {
            while (waxOn == false)
                condition.await();
        } finally {
            lock.unlock();
        }
    }

    public void waitForBuffing() throws InterruptedException {
        lock.lock();
        try {
            while (waxOn == true)
                condition.await();
        } finally {
            lock.unlock();
        }
    }
}

class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                printnb("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax On task");
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                printnb("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax Off task");
    }
}

public class WaxOMatic2 {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
} /* Output: (90% match)
Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Exiting via interrupt
Ending Wax Off task
Exiting via interrupt
Ending Wax On task
*///:~
