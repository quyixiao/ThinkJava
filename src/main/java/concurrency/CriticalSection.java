//: concurrency/CriticalSection.java
// Synchronizing blocks instead of entire methods. Also
// demonstrates protection of a non-thread-safe class
// with a thread-safe one.
package concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*****
 * 685 页
 *
 *      有时，你只是希望防止多个线程同时访问方法内部的部分代码面不是防止访问整个方法，通过这种方式分离出来的代码段被临界区，它也使用
 * synchronized关键字建立，这里，synchronized被用来指定某个对象，此对象的锁被用来对花括号内的代码进行同步控制。
 *      synchronized(synchObject t){
 *          //this code can be accessed
 *          // by only one task at a time
 *      }
 *      这也被称为同步控制块，在进入此段代码前，必须得到syncObject对象锁，如果其他线程己经得到这个锁，那么就得等到锁被释放以后
 * 才能进入临界区
 *      通过使用同步控制块，而不是对整个方法进行同步控制，可以使用多个任务访问对象的时间，性能得到显著的提高，下面的例子
 *  比较了这两种同步控制方法，此外，它也演示了如何把一个非保护的类型的类，在其他的类的保护下和控制下，应用于多线程来环境
 *
 *      正如注释中注明的，Pair不是线程安全的，因为它的约束条件，虽然是任意的，需要两个变量要维护相同的值，此外，如本章前面所述
 * 自增加操作不是线程安全的，并且因为没有任何方法被标记为synchronized，所以，不能保证一个Pair对象在多线程安全的Pair类，而你需要在一个线程
 * 环境中使用它，通过创建PaireManager类就可以实现这一点，PaireManager类持有一个Pair对象并控制它的一切访问，注意唯一的public方法是getPair()
 * ，它是synchronized的，对于抽象方法increment()，对increment()的同步控制将实现的时候进行处理。
 *      至于PairManager类的结构 ，它的一些功能在基类实现，并且其一个或多个抽象方法在派生类定义，这种结构 在设计模式中称为模板方法，设计模式
 * 使你得以把变量封装在代码里，在此，发生变化的部分是模板方法increment()，在PaireManager1中，整个increment()方法是被同步控制的，但在PaireManager2中
 * ，increment()方法使用同步控制块进行同步，注意是被同步控制 的，但是在PaireManager2中，increment()方法使用同步控制块进行同步，
 * 注意increment()，对increment()的同步控制将在实现的时候进行处理
 *      至于PaireManager类的结构，它的一些功能在其类中实现，并且其一个或多个抽象方法在派生类中定义，这种结构在设计模式中称为模板方法
 * ，设计模式使你得以把变化封装在代码里，在此，发生变化 的部分是模板方法increment()，在PairManaager1中，整个increment()方法是被同步
 * 控制的，但在PairManager2中，increment()方法使用同步控制块进行同步，synchronized关键字不属于方法特征签名的组成部分，所以可以在覆盖
 * 方法的时候加上去。
 *      store()方法将一个Pair对象添加到了synchronized ArrayList中，所以这个操作是线程安全的，因此方法不必进行防护，以以放置在PaireMnagere2
 * 在synchronized语句块的外部。
 *      PaireManipulator被创建用来测试两种不同类型的PaireMnager，其方法是在某个傻中调用的incremnet()，而PaireChecker则在另一个任务中执行，为了跟踪
 * 可以运行测试的频度，PaireChecker在每次成功时都递增CheckCouinter，在main()中创建了两个PaireManIputlator对象，并允许它们运行一段时间，之后每个
 * PairManager的结果会得到展示 。
 *      尽管每次运行的结果可能会非常不同，但一般来说，对于PairCheckedr的检查频率，PairManager1.increment()不允许有PaireManager2.increment()那样多
 * 后者受用同步控制块进行控制的典型原因，使得其他线程更多的访问（在它俩的情况下尽可能多）
 *      你还可以使用显式的Lock对象来创建临界区
 *
 *
 *
 *
 * 1
 *
 *
 *
 */
class Pair { // Not thread-safe
    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementX() {
        x++;
    }

    public void incrementY() {
        y++;
    }

    public String toString() {
        return "x: " + x + ", y: " + y;
    }

    public class PairValuesNotEqualException
            extends RuntimeException {
        public PairValuesNotEqualException() {
            super("Pair values not equal: " + Pair.this);
        }
    }

    // Arbitrary invariant -- both variables must be equal:
    public void checkState() {
        if (x != y)
            throw new PairValuesNotEqualException();
    }
}

// Protect a Pair inside a thread-safe class:
abstract class PairManager {
    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair p = new Pair();
    private List<Pair> storage =
            Collections.synchronizedList(new ArrayList<Pair>());

    public synchronized Pair getPair() {
        // Make a copy to keep the original safe:
        return new Pair(p.getX(), p.getY());
    }

    // Assume this is a time consuming operation
    protected void store(Pair p) {
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException ignore) {
        }
    }

    public abstract void increment();
}

// Synchronize the entire method:
class PairManager1 extends PairManager {
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}

// Use a critical section:
class PairManager2 extends PairManager {
    public void increment() {
        Pair temp;
        synchronized (this) {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        }
        store(temp);
    }
}

class PairManipulator implements Runnable {
    private PairManager pm;

    public PairManipulator(PairManager pm) {
        this.pm = pm;
    }

    public void run() {
        while (true)
            pm.increment();
    }

    public String toString() {
        return "Pair: " + pm.getPair() +
                " checkCounter = " + pm.checkCounter.get();
    }
}

class PairChecker implements Runnable {
    private PairManager pm;

    public PairChecker(PairManager pm) {
        this.pm = pm;
    }

    public void run() {
        while (true) {
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}

public class CriticalSection {
    // Test the two different approaches:
    static void
    testApproaches(PairManager pman1, PairManager pman2) {
        ExecutorService exec = Executors.newCachedThreadPool();
        PairManipulator
                pm1 = new PairManipulator(pman1),
                pm2 = new PairManipulator(pman2);
        PairChecker
                pcheck1 = new PairChecker(pman1),
                pcheck2 = new PairChecker(pman2);
        exec.execute(pm1);
        exec.execute(pm2);
        exec.execute(pcheck1);
        exec.execute(pcheck2);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
        System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager
                pman1 = new PairManager1(),
                pman2 = new PairManager2();
        testApproaches(pman1, pman2);
    }
}




/* Output: (Sample)
pm1: Pair: x: 15, y: 15 checkCounter = 272565
pm2: Pair: x: 16, y: 16 checkCounter = 3956974
*///:~
