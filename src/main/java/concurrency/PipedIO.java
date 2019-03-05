package concurrency;//: concurrency/PipedIO.java
// Using pipes for inter-task I/O

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/****
 * 717页
 *      Toast是一个使用enum值的优秀示例，注意，这个示例中没有任何显式的同步，即使用Lock对象
 *  或synchronized关键字的同步），因为同步由队列（其内部是同步的）和系统的设计隐式地
 *  管理了，每片Toast在任务时刻都只由一个任务在操作，因为队列的阻塞，使得处理过程将被
 *  自动的挂起和恢复，你可以看到由BlockingQueue产生的简化十分明显，在使用显式的wait()和
 *  notifyAll()时存在的类和类之间的耦合被消除了，因为每个类都只和它的BlockingQueue通信。
 *      任务间使用管道进行输入/输出
 *      通过输入/输出在线程间进行通信通常是很有用，提供线程功能的类库以 管道的形式对线
 *  程间的输入，输出提供了支持，它们在Java输入/输出类库中的对应物就是PipedWriter类（允许任务
 *  向管道写）和PipedReader类（允许不同任务从同一个管道中读取），这个模型可以看成是
 *  "生产者-消费者"问题的变体，这里的管道就是一个封装好的解决方案，管道基本上是一个阻塞队列
 *  存在于多个引入BlockingQueue之前的Java版本中。
 *      下面是一个简单例子，两个任务使用一个管道进行通信，
 *      Sender和Receive代表了需要互相通信两任务，Sender创建了一个PiperWiter，它是一个单独的
 *  对象，但是对于Receiver，PipedReader的建立必须在构造器中与一个PipedWriter相关联
 *  Sender把数据放进Writer，然后一段时间，（随机数）然而，Receiver没有，Sleep()和
 *  wait()，但当他调用read()时，如果没更多的数据，管道将自动阻塞。
 *      注意sender和receiver是在main()中启动的即对象构造彻底完毕以后，如果你启动了一个
 *  没有构造完毕的对象，在不同的平台上管道可能会产生不一到我的行为，注意，BlockingQueque
 *  使用起来更加健壮而容易。
 *      在shUtDownNow()被调用时，可以看出PipedReader与普通I/O之间最重要的差异，Piped-Rreader
 *   是可中断的，如果你将in.read()调用修改为system.in.read()，那么interruput()将不能打断read()
 *   调用
 *      死锁
 *      现在你理解了，一个对象可以有synchroized方法或其他的形式的加锁机制来防止别的任务在
 *  互斥还没有释放的时候就访问这个对象，你己经学习过了，任务可以变成阻塞状态，所以就可能
 *  出现这种情况，某个任务在等待另一个任务，而后者以等待另任务，这样一直下去，直到这个
 *  链条上的任务以在等待第一个任务释放锁，这得到一个任务之间等待的连续循环。
 *  有哪个线程能继续，被称为死锁，
 *      如果你运行了一个程序，而它马上就死锁了，你可以立即跟踪下去，真正的问题在于，程序可
 *  能看起来工作良好，但是具有潜在的死锁危险，这时，死锁可能发生，而事先却没有任务征兆，所以
 *  缺陷会潜伏在你的程序里，直到客户发现它出乎意料的发生，以一种几乎肯定是
 *  很难重现的方式发生，因此，在编写并并发程序的时候，进行仔细的程序设计以防止死锁是关
 *  键部分。
 *      由Edusge Dijkstra提出的哲学家就餐问题是一个经典的死锁债主，该问题的基本描述中是指定
 *  五个哲学家，不过这里的例子中将允许什么问题数目，这些哲学家将花部分时间思考，花部分时
 *  间就餐，当他们思考的时候，不需要任何共享资源，但当他们就餐时，将使用有限数量的餐具，
 *  在问题的原始描述中，餐具就是叉子，要吃到桌子的中央拉盘子里意大利面条需要用两把叉子，不
 *  过的餐具看成是筷子更合理，很明显，哲学家就餐就需要两根筷子。
 *      问题中引入的难点是，作为哲学家，他们很穷，所以他们只能买一根筷子，更一般的讲，
 *   筷子的哲学家的数据相同，他们围坐在桌子周围，每个之间放一根筷子，当一个哲学家要就餐
 *   时，这个哲学家必须同时得到左右两边的筷子，如果一个哲学家左边或右边已经有人在
 *   使用筷子了，那么这个哲学家就必须等待，直到可等到必需的筷子。
 *
 *
 *
 *
 *
 *   1
 *
 *
 *
 *
 */
class Sender implements Runnable {
    private Random rand = new Random(47);
    private PipedWriter out = new PipedWriter();

    public PipedWriter getPipedWriter() {
        return out;
    }

    public void run() {
        try {
            while (true)
                for (char c = 'A'; c <= 'z'; c++) {
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
        } catch (IOException e) {
            print(e + " Sender write exception");
        } catch (InterruptedException e) {
            print(e + " Sender sleep interrupted");
        }
    }
}

class Receiver implements Runnable {
    private PipedReader in;

    public Receiver(Sender sender) throws IOException {
        in = new PipedReader(sender.getPipedWriter());
    }

    public void run() {
        try {
            while (true) {
                // Blocks until characters are there:
                printnb("Read: " + (char) in.read() + ", ");
            }
        } catch (IOException e) {
            print(e + " Receiver read exception");
        }
    }
}

public class PipedIO {
    public static void main(String[] args) throws Exception {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }
} /* Output: (65% match)
Read: A, Read: B, Read: C, Read: D, Read: E, Read: F, Read: G, Read: H, Read: I, Read: J, Read: K, Read: L, Read: M, java.lang.InterruptedException: sleep interrupted Sender sleep interrupted
java.io.InterruptedIOException Receiver read exception
*///:~
