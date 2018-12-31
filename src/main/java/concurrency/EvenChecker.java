package concurrency;//: concurrency/EvenChecker.java

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/***
 *
 *
 * 677页
 *
 *      注意 ，在本例中，可能被撤销的类不是Runnalble，而所有依赖于IntGenerator对象的，EvenChecker任务，将测试它，以查看它是否它是己经
 * 被撤销，正如你在run()中所见，通过这种方式，共享公共资源的任务可以可以观察该资源的终止信号，这可以消除所谓竞争条件，即两个或更多的
 * 任务竞争响应某个条件，因此产生冲突或不一致结果的情况，你必须仔细考虑，并防并发系统失败的所可能途径，例如，一个任务不能依赖于另一个
 * 任务，因为任务关闭的顺序无法得到保证，这里，通过使用任务依赖于非任务对象，我们可以消除潜在的竞争条件
 *      test()方法通过启动大量使用相同的intGenerator的EvenChecker,设置并执行对任何类型的IntGenerator测试，如果IntGenerator引发
 *      失败，那么test()将报告它并返回，否则，你必须按下Control-c来终止它。
 *      EventChecket任务总是读取和测试从与其相关的intGenerator返回的值，注意，如果 generator.isCanceled()为true,则run()将返回，这将
 *      告知EvenChecker.test()中的Executor该任务完成了，任务EvenChecker任务都可以与其他的关联得体地关闭，在后面各节中，你将看到java包含
 *      的用于线程终止的各种更通用的机制
 *      我们看到第一个IntGenerator有一个可以产生一系列偶数值的next()方法
 *
 *  一个任务有可能在另一个任务执行第一个对currentEventValue的递增操作之后，但是没有执行第二个操作之前，调用next()方法，即代码中被注释为
 *  Dange point here 的地方，这将使这个值处于不恰当状态，为了证明这是可能发生的，EvenChecker.test()创建了一组EvenChecker对象，以连续的
 *  读取并输出了同一个EvenGenerator，并测试检查每个数值是否都是偶数，如果不是，就会报告错误，而程序也将关闭
 *
 *  有一点很重要，那就是要注意到递增程序自身需要多个步骤，并且在递增程序自身也需要多个步骤，并且在递增过程中任务可能会被线程机制挂起，也就是
 *  说，在java中，递增不是原子性的操作，因此，如果不保护任务可能会被线程机制挂起，也就是说，在java中递增不是原子性的操作，因此，如果不保护
 *  任务，骄阳似我单一的递增也是不安全的。
 *      前面的示例展示了使用线程时的一个基本的问题，你永远都不知道一个线程何时在运行，想象一下，你坐在桌边手拿叉子，正要去叉盘中了最后的最后的一片
 *  食物，当你的叉子就要够着它时，这片食物食物突然消失了，因为你的线程被挂起了，而另一个餐者进入并吃掉了它，这正是在你编写程序时需要处理的问题，
 *  对于并发工作，你需要某种方式来防止两个任务访问相同的资源，至少在关键阶段不能出现这种情况。
 *      防止这种冲突的方法就是当资源被一个任务使用时，在其上加锁，第一个访问某项资源的任务必须 锁定这项资源，李一鸣 其他任务在其被解锁之前，
    就无法访问它了，而在其被解锁之时，另一个任务就可能锁定并使用它，以此类推，如果汽车前排座位是受限资源，那么大喊着，冲呀，的孩子就会，在
 这次旅途过程中，获取其上的锁。
    基本上所有的并发模式在解决线程冲突问题的时候，都是采用序列化访问共享资源的方案，这意味着在给定时刻只允许一个任务访问其共享资源，通常这是通过在
 代码前面加上一条锁语句来实现的，这就使得在一段时间内只有一个任务可能运行这段代码，因为锁平底锅产生了一种互相排斥的效果，所以这种机制常常称为互斥量
考虑一下屋子里的浴室，多个人，即多个由线程驱动的任务，都希望能单独使用浴室，即共享资源，为了使用浴室，一个人先敲门，看看是否能使用，如果没人的话，他就进入
 浴室并锁上门，这时话，就会被阻挡，所以他们要在浴室门口等侍，直到浴室可以使用。
    当浴室使用完毕，就该把浴室给其他人使用了，别的任务就可以访问资源了，这个比喻就有点不太准确了，事实上，人们并没有排队，我们也不能确定谁确定将是下
 一个使用浴室的人，因为线程高度机制并不是确定性的，实际情况是，等待使用浴室的人们族拥在浴室门口，当锁住浴室门的那个人打开锁准备离开时候，离门最近的那个
 人可能进入浴室，如前所述，可以通过yidld()和setPriority()来给一个线程高度器提供建议，但这些建议未必会有多大效果，这取决于你的具体平台和JVM实现
    java以提供关键字synchronized的形式，为防止资源冲突提供了内置支持，当任务要执行被synchronized关键字保护的代码片段的时候，它将检查锁是否可用，然后获取
 锁是否可能，然后获取锁，执行代码，释放锁
    共享资源一般是以对象形式存在的内存片段，但也可能是文件，输入输出端口，或者打印机，要控制对共享资源访问，得先把它包装进一个对象，然后把所有要访问这个资源
 的方法标记为synchronized，如果某个任务处于为synchronized的方法的调用中，那么在这个线程从该方法返回之前，其他所有要调用类中，任何标记为synchronized方法
 的线程都会被阻塞。
    在生成偶数代码中，你己经看到了，你己经看到了，你应该将类的数据成员都声明为private的，而且只能通过方法来访问这些数据，所以可能把方法标记为synchronized来
 防止资源冲突，下面是声明synchronized方法方式。
    sysnchroized void f() {
    synchronized void g() {}
    所有对象都自动含有单一的锁，也称为监视器，当在对象上调用其余synchronized 方法的时候，此对象都被加锁，这时该对象上的其他synchronized方法只有等到前一个
 方法调用完毕并释放了锁之后才能被调用，对于前面的方法，如果 某个任务对对象调用了f()，对于同一个对象而言，就只能等到f() 调用结束并释放了锁之后，其他任务才能
 调用f()和g()，所以，对于某个的制定对象来说，其所有的synchronized方法共享同一个锁，这可以被用防止多个任务同时访问被编码为对象内存。
    注意，在使用并发时，将域设置为private是非常重要的，否则，synchronized关键字就不能防止其他任务直接访问域，这样就会产生冲突。
    一个任务可以多次获得对象的锁，如果 一个方法在同一个对象上调用了第二个方法，后者又调用了同一对象上的另一个方法，就会发生这种情况，JVM负责跟踪对象被加锁的
 时候，计数变1，每当这个相同的任务在这个对象上获得锁时，计数都会递增，显然 ，只有首先获得了锁的任务才能允许继续获取多个锁，每当任务离开一个synchronized方法
 计递减，当计数为零的时候，锁被完全释放，此时别的人任务就可以使用此资源。
    针对每个类，也有一个锁作为类的Class对象的一部分，所以synchronized static 方法可以在类的范围内防止对static数据的并发访问。
    如果你正在写一个变时 ，它可能接下来将被另一个线程读取，或者正在读取一个上一次己经被另一个线程写过的变量，那么你必须使用同步，并且，读写线程都必须
 用相同的监视器锁同步
    如果在你的类中有超过一个方法在处理临界数据，那么你必须 同步所有相关的应该没有，如果只同步一个方法，那么其他方法将会随意地忽略这个对象锁，并可以在无
 任何惩罚的情况下被调用这是很重要的点，每个访问临界共享资源的方法都必须 被同步，否则它们就不会正确地工作。

 
 *
 */
public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator g, int ident) {
        generator = g;
        id = ident;
    }

    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not even!");
                generator.cancel(); // Cancels all EvenCheckers
            }
        }
    }

    // Test any type of IntGenerator:
    public static void test(IntGenerator gp, int count) {
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++)
            exec.execute(new EvenChecker(gp, i));
        exec.shutdown();
    }

    // Default value for count:
    public static void test(IntGenerator gp) {
        test(gp, 10);
    }
} ///:~
