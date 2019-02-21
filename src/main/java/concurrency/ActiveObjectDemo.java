package concurrency;

//: concurrency/ActiveObjectDemo.java
// Can only pass constants, immutables, "disconnected
// objects," or other active objects as arguments
// to asynch methods.

import java.util.concurrent.*;
import java.util.*;

import static net.mindview.util.Print.*;


/******
 *
 * 763页
 *
 *
 * 活动对象
 *      当你通读本章之后，可能会发现，java中的线程看起来非常复杂并难以正确使用，另外，它
 *  好象还有点达不到预期效果的味道，尽管多个任务可以并行工作，但是你必须花很大的气力
 *  去实现防止这些任务彼此互相干涉的技术
 *      如果你曾经编写过汇编语言，那么编写多线程程序就似曾相识，每个细节都很重要，你有
 *  你有责任处理所有事物，并且没有任务编译器检查程序的安全防护措施。
 *      是多线程模型自身有的问题吗？毕竟，它是牌过程型编程世界，并且几乎没做什么改变
 *  可能还存在着另一个不同的并发模型，它更加的适合面向对象的编程。
 *      有一种可替换的方式被称为活动对象或行动者，之所以称这些对象是活动的，是因为每个对象都
 *  维护着它自己的工作器线程和消息队列，并且所有的对象的请求都将进入队列的排队，任何时刻都
 *  只能运行其中的一个，因此，有了活动对象，我们就可以串行化消息而不是方法，这
 *  意味着不再需要防备一个任务在其循环的中间的被中断对象编程。
 *      有一种可替换的方式就是被称为活动对象或行动者，之所以称这些对象的请求都将进入队列排队
 *  ，这意味着不同埋需要防备一个任务在其循环循环的中间被中断这种问题。
 *      当你向一个活动对象发送消息时，这条消息会转变为一个任务，该任务会被插入到这个对象
 *   队列中，等待在以后的截个时刻运行，java SEt5 的futrue在实现这种模式时将派上用场，下
 *   面是一个简单的示例，它有两个方法，可将方法调用排进队列。
 *      由于对Executors.netSingglelThreadExecutor()的调用产生的间线程执行器维护着它自己的
 *   无界，阻塞队列，并且只有一个线程从该队列中取走任务并执行它们直至完成，我们需要在
 *   calculateInt()和calculateFloat()中做的就是用submint()提交一个新的Callable对象，以响应对
 *   这些方法的调用，这样就可以方法调用转变为消息，而submit的方法体包含在茂名内部类的call()
 *   方法中，注意，每个活动对象的返回值都是一个具有泛型参数的Future，而这个泛型参数就
 *   是该方法中实际的返回类型，通过这种方式，方法调用几乎可以立即返回，调用者可以使用
 *   Future来发现何时任务完成，并收集实际的返回值，这样可以处理最复杂的情况，但是如果调用
 *   没有任何返回值，那么这个过程将简化。
 *      在main()中，创建一人个List<Futrue<?>> 来捕获由发送给活动对象的CalculateGloat()
 *   和calculateInt()消息返回的Futrue对象，对于每个Futrue，都是使用IsDone()来从这个列表中抽取的
 *   这种方式使得当Futrue完成并且其结果被处理过之后，就会从List中移除，注意，使用
 *   CopyOnWriteArrayList可以移除为了防止ComconrrentModifiactionException而复制List的这种需求
 *      为了能够在不经意间就可以防止这些上线程之间的耦合，任何传递给活动对象方法调用的都必须是
 *   只读的其他活动对象，或者是是不连接对象 我的术语，即没有连接任何其他的任务的对象，
 *   这一点很难强制保障，因为没有任何语言支持它，有了活动对象。
 *      每个对象者可以拥有自己的工作器线程。
 *      每个对象都维护对它自己的域的全部控制权，这比普通的类要理严苛一些，普通 的类只是拥有防护它们的域的选择权。
 *   所有的活动对象之间的通信都将以在这些对象之间的消息形式发生。
 *      活动对象之间的所有消息都要排队
 *      这些结果很吸引人，由于从珍上活动对象到另一个活动对象的消息只能被排队时的延迟所
 *  阻塞，并且国为这个延迟总是非常短且然独立于任何其他的对象，所以发送消息实际上是不可阻塞的
 *  （最坏的情况也只是很很短的延迟），由于一个活动对象系统只是经由消息来通信，所以两个对
 *  象在竞争调用另一个对象上的方法时，是不会被阻塞的，而这意味着不会发生死锁，这是一种巨大的韩波，因为在活动
 *  对象中的工作器线程在任何时刻只执行一个消息，所以不存在任何资源竞争，而你也不必操心应该如何同步
 *  方法，同步仍旧发生，但是它通过将方法调用排队，
 *      使得任何显示该都只能发生一个调用，从而将同步控制在消息级别上发生。
 *     遗憾的是，如果没有直接的编译器支持，上面的这种编码方式实在是太过于麻烦了，但是，
 * 这在活动对象和行动者领域，或者还是有趣的被称为基于代理的编程领域，确实产生了进步，代理
 * 实际上就是活动对象，但是代理系统还支持跨网络的和机器在的透明性，如果代理编程最终成为
 * 面向对象编程的继承者，我一点也不会觉得惊讶，因为它对象和相对容易的并发解决方案结合了起来。
 *      参赛搜索Web，你公发现更多有关活动对象，先去者或代理的信息，特别是某些活动对象幕后的
 *  概念，它们来自C.A.R Hoare的通信顺序进程理论。
 *
 *
 *
 *
 *     总结
 *   本章的目标是向你提供使用Java线程进行并发程序设计的基础知识，以全你理解
 *   1，可以运行多个独立的任务
 *   2. 必须考虑当这些任务关闭时，可能出现的所有问题
 *   3. 任务可能会在共享资源上彼此干涉，互斥，锁，是用来防止这种冲突的基本工具。
 *   4.如果任务设计得不够仔细，就有可能会死锁
 *   明白了什么时候应该使用并发，什么时候，应该蜜兔使用并发的非常关键的，使用它的原因主要是 ：
 *   要处理很多任务，它们交织在一起，应用并发能够更有效地使用计算机（包括在多个CPU上透明的分配任务的能力）
 *   要能够更好地组织代码
 *   要更便于用户使用
 *   均衡资源的经典案例是在等待输入/输出时使用CPU,更好的代码组织可以在仿真中看到
 *   使用户方便的经典安全是在长时间的下载过程中监视，停止 ，按钮是否被按下
 *   线程的一个额外好处就是它们提供了轻量级的执行上下文切换（大约100条指令），而不是重
 *   量级的进程上下文切换，要上千条指令，因为一个给定的进程内的所有线程共享相同的内存空间
 *   轻量级的上下文切换要上千条指令，因为一个给定进程内的所有线程共享相同的内存空间
 *   轻量级的上下文切换只是改变了程序的执行序列和局部变量，进程切换（重量级的上下文切换）
 *   必须改变所有的内存空间
 *   多线程的主要缺陷有
 *   1.等待共享资源的时候性能降低
 *   2.需要处理线程的额外CPU花费
 *   3.糟糕的程序设计导致不必要的复杂度
 *   有可能产生一些病态行为，如饿死，竞争，死锁和活锁（多个运行各自任务的线程使得整体无法完成）
 *   不同平台导致的不一致，比如，我在编写书中的一些例子时发现，竞争条件在某些机器上很快出的出现
 *   ，但是在别的机器上根本不出现，如果你在后一种机器上做研发，那么当你发布程序时候就要大吃一惊。
 *   因为多线程共享资源，这就是线程产生的最大难题，这需要明智的使用可能的加锁机制，（例如
 *   synchronized 关键字）,它们仅仅是个工具，同时它们会引入潜在死锁的条件，所以要参它们有透彻的
 *   理解
 *      此外，线程应用上也有一些技巧，Java允许你建立足够锪对象来解决你的问题，至少理论上
 *   是如此，（实际上并非如此，比如，为工程上的有限元素分析而创建几百万个对象在Java中，如果不使用
 *   离远设计模式，是并不可行的）然而，你要地的线程数目看起来还是有个上界的，因为达到了一定数数量之后
 *   ，线程性能会很差，这个临界点很难检测，通常依赖于操作系统和JVM，它可以是不足一百个线程，也可能是
 *   几千个线程，不过通常我们只是创建少数线程来解决问题，所以限制并不严重，尽管对于更一般的设计来说，
 *   这可能会是一个约束，它可能会强制要求你添加协助并发模式
 *      不管在使用某种特定的语言或类库时，线程机制看起来是多么的简单，你都应该视其为魔法，
 *    总有一些你最不想碰见的事物会反噬你一口，哲学家用餐问题之所以有趣，就是因为它可以进行调整，
 *    使得死锁极少发生，这给一个印象，每件事物都很美好
 *      通常线程机制要非常仔细和保守，如果你的线程问题变得大而复杂，那么就应该
 *    考虑使用像Eralng这样的语言，这是专门的助于线程机制的几种函数型语言之一，你可以将这种语言用于
 *    程序中要求使用的线程机制的部分，前题是你经常使用线程机制，或者线程问题复杂度足以促使你这么做
 *    测试
 *
 *    测试
 *
 *
 *
 *
 *
 *
 * 1
 *
 *
 */
public class ActiveObjectDemo {
    private ExecutorService ex = Executors.newSingleThreadExecutor();
    private Random rand = new Random(47);

    // Insert a random delay to produce the effect
    // of a calculation time:
    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
        } catch (InterruptedException e) {
            print("sleep() interrupted");
        }
    }

    public Future<Integer> calculateInt(final int x, final int y) {
        return ex.submit(new Callable<Integer>() {
            public Integer call() {
                print("starting111111 " + x + " + " + y);
                pause(500);
                return x + y;
            }
        });
    }

    public Future<Float> calculateFloat(final float x, final float y) {
        return ex.submit(new Callable<Float>() {
            public Float call() {
                print("starting222222 " + x + " + " + y);
                pause(2000);
                return x + y;
            }
        });
    }

    public void shutdown() {
        ex.shutdown();
    }

    public static void main(String[] args) {
        ActiveObjectDemo d1 = new ActiveObjectDemo();
        // Prevents ConcurrentModificationException:
        List<Future<?>> results = new CopyOnWriteArrayList<Future<?>>();
        for (float f = 0.0f; f < 1.0f; f += 0.2f)
            results.add(d1.calculateFloat(f, f));
        for (int i = 0; i < 5; i++)
            results.add(d1.calculateInt(i, i));


        print("All asynch calls made");


        while (results.size() > 0) {
            for (Future<?> f : results)
                if (f.isDone()) {
                    try {
                        print(f.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    results.remove(f);
                }
        }
        d1.shutdown();
    }
}




/* Output: (85% match)
All asynch calls made
starting 0.0 + 0.0
starting 0.2 + 0.2
0.0
starting 0.4 + 0.4
0.4
starting 0.6 + 0.6
0.8
starting 0.8 + 0.8
1.2
starting 0 + 0
1.6
starting 1 + 1
0
starting 2 + 2
2
starting 3 + 3
4
starting 4 + 4
6
8
*///:~
