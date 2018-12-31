package concurrency;
//: concurrency/AttemptLocking.java
// Locks in the concurrent library allow you
// to give up on trying to acquire a lock.

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/***
 * 680页
 *
 *      ReentrantLock允许你尝试着获取但是最终未获取锁，这样如果其他人己经获取了这个锁，你就可以决定离开去执行其他的一些事情
 * ,而不是等待直到这个锁被释放，就像在untimed()方法中所看到的，在timeed()中，做出了尝试去获取锁，该尝试可以在2秒之后失败，注意使用了
 * java SE5的timeUnit类来指定时间单位，在main()中，作为匿名类而创建了一个单独的Thread它将获取锁，这使用untimed()和timed()方法对某些地事物将
 * 产生竞争
 *      原子性与易变性
 *  在有关java线程的讨论中，一个常不正确的我咋说啊是，原子操作不需要进行同控制，操作是不能被线程高度机制中断的操作的，一旦操作开始，那么它一定
 *  可以在可能发生的，上下文切换 之区域的到其他的线程执行，执行完毕，依赖于原子性是很棘手的且很危险的，如果你是一个并发专家，或者你得到了
 *  专家的帮助，你才应该使用原子性来代替同步，如果你夜炫自己足够聪明，以以就会这种玩为似的情况，那么请的亲爱下面的测试，
 *      Goetz测试，如果你可以编写用于现代微处理器的调性能jvm，那么就有资格去考虑是否避免同步。
 *      了解原子性是很有用的，并且要知道原子性与其他的高级技术是一道，在java.util.concurrent类库中己经实现了，但是jvm可以将64位
 *      的读取和写入当作两个分离32位操作内存，但是JVM可以将64位（long和double变量）的读取和写入当作不可分（原子）的操作来执行，这就
 * 产生了在一个读取和写入操作中间发生上下文切换，从而可以看到不同的任务不正确的结果的可能性，这有时被称为字撕裂，因为你可能会看到部分被修改过的
 * 数值，但是，当你定义long或double变量时，如果使用了volatitle关键字，就会获得简单的赋值与返回操作，原子性，注意，在javaSEt之前，volatitle一
 * 直未能正确的获得地正确的工作，不同的JVM可任意的提供更强的保证，但是你不应该依赖于平台相关的特性
 *      因此，原子操作可由线程机制来保证其不可中断，专家级的程序员可能利用这一点来编写无锁的代码，这些代码不需要被同步，但是即便是这样，这也是
 * 一种过于简化的机制，在时甚至看起来应该是安全的原子操作，实际上也可能不安全，本书的读者通常不能通过前面提及的Goetz测试，因此也就不具备用原子
 * 操作来替换不同步的能力，尝试移除不同步通常是一种表示不成熟优化的信号，并肯将会给你搜到大量的麻烦，而你却可能没有收获多少好处，甚至压根没有任何
 * 好处，
 *      在多处理系统，现在以多核处理器的的形式出现，即在单个芯片上有多个CPU，上，相对于单处理器系统而言，可视性问题远比原子性问题多得多，一个任务做出
 * 的修改，即使在不中于间处理器系统而言，可视性问题远比原子性问题多得多，一个任务做出的修改，即使不在不中断的问询上讲是原子性的，对其他的任务也可能是不可视的
 * 例如，修改只是暂时的存储在本处理器在系统中，因此不同的任务对应用的新动态有不同的视图，另一方面，同步机制强制在处理器系统中，一个任务做出的修改必须在应用中是可视的
 * 如果 没有同步机制，那么修改时可视将无法确定。
 *      volatitle关键字还确保了应用跌可视性，如果你将一个域声明为vvolatile的，那么只要对这个域产生了写操作，那么所有的读操作就都可以看到这个修改，即便使用了本地缓存
 * ，情况也确实如此，volati域会立即被写入主存中，而读操作就都可以看到这个修改，即便使用了本地缓存，情况也确实如此，volatile域会立即被写入到主存中，面读取操作就以
 * 发生在主存中。
 *  理解原子性和易变性是不同的概念，这一点很重要，在非volatitle域上的的原子操作可必刷新到主存中去，因此其他读取该域的任务也不必看到这个新值，如果多个任务在同时访问某个域
 *  那么这个域就应该是volatitle的，否则，这个域就应该只能经由同步来访问，同步也会导致向该在中刷新，因此如果 一个域完全由synchronize方法或应该够块来孩气，那就不必将其设置为是
 *  volatle的
 *      一个任务所作的任何写入操作对这个任务为来说都是可视的，因此如果 它只需要在这个任务内部可视，那么你就不需要将其设置为volatile的
 *      当一个域的依赖于它之前的值进，例如递增一个可视，例如递增一个计数器，volatitle就无法工作了，如果 某个域的值受到其他域的值的限制，那么volatile也无法医药代表，例如
 * Range类的lower和upper边界就必须遵循lower<=upper的限制。
 *      使用volatile而不是synchronized的唯一安全的情况是类中只有一个可变的域，再一次提醒你的选择应该是使用synchronized关键字，这是最安全的方式，而尝试其他他任何方式都
 * 是有风险的。
 *  什么才属于原子操作呢，对域中的值做赋值和返回操作通常都原子性的，但是，在c++ 工作到下面的操作老阳 可能是原子性的
 *      i++;
 *      i += 2;
 *
 * 但是在c++中，这要取决 于编译器和处理器，你无法编写出依赖于原子性的C++只卖平台代码，因为c++没有像java那样一致的内存模型
 *      在java中，上面的操作肯定不是原子性的，正如从下面的应该没有所产生的JVM指令中可以看到的那样
 *      每条指令都会产生一个get和put，它们之间还有一些其他的指令，因此在获取和放置之间，另一个任务可能会修改这个域，所以，
 *  这些操作不是原子性的
 *
 *
 *
 */
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        } finally {
            if (captured)
                lock.unlock();
        }
    }

    public void timed() {
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS): " +
                    captured);
        } finally {
            if (captured)
                lock.unlock();
        }
    }

    public static void main(String[] args) {
        final AttemptLocking al = new AttemptLocking();
        al.untimed(); // True -- lock is available
        al.timed();   // True -- lock is available
        // Now create a separate task to grab the lock:
        new Thread() {
            {
                setDaemon(true);
            }

            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        Thread.yield(); // Give the 2nd task a chance
        al.untimed(); // False -- lock grabbed by task
        al.timed();   // False -- lock grabbed by task
    }
} /* Output:
tryLock(): true
tryLock(2, TimeUnit.SECONDS): true
acquired
tryLock(): false
tryLock(2, TimeUnit.SECONDS): false
*///:~
