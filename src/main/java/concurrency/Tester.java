package concurrency;//: concurrency/Tester.java
// Framework to test performance of concurrency containers.

import net.mindview.util.Generated;
import net.mindview.util.RandomGenerator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/******
 *
 * 754页
 * 免锁容器
 就像在第11章中所强调的，容器是所有编程中的基础工具，这其中自然也包括并发编程，
 出于这个原因，像Vector和Hashtable这类早期容器具有许多的synchronized方法，当它
 们用于非多线程的测试用程序中时，会不会导致不可的接受的开销，在java1.2中，新容器
 类库是不同步的，并且在Collections类提供了各种static的同步的装饰方法，从而来同步
 不同的类型的容器，尽管这是一种改进，因为它使你可以选择在斧容器是否要要不用同步，但是
 这各开销仍旧是基于synchroized加锁机制的，javaSet5 的别添加了新容器，通过使用
 更灵巧的技术来消除加锁的，从而提高线程安全的性能。
    这些免锁容器背后能用策略是，对容器的修改可以与读取操作同时发生，只想要读取者
 能够看到完成修改的结果即可，修改是在容器数据结构的某个部分的一个单独的副本，有时是整个
 数据结构的副本，上执行的，并且这个副本在修改过程中是一可视的，只有当修改完成时，
 被修改的结构才会自动地与主数据结构进行交换，之后读取者就可以看到这个修改了。
    在CopyOnWriteArrayList中，写入将导致创建整个底层数组的副本，而源数组将保留在原地
 ，使得复制的数组在被修改时，读取操作可以安全的地执行，当修改完成时，一个原子的操作
 将把新的数组换入，使得新发读取操作可以看到这个新的修改，CopyOnWriteArrayList的好
 处之一是当多个迭代器同时遍历和修改这个列表时，不会抛出ConcurrentModificationExcetion
 因此你必编写特殊的代码去防范这个异常，就像你以前必须作的那样
    CopyOnWriteArraySet将使用CopyOnWriteArrayList来实现其免锁行为。
    ConcurrentHashMap和ConcurrentLinkedQueue使用了类似的技术，允许并发的读取和写入，
 但是容器中只有部分内容而不是整个容器中有部分内容不是整个容器可以被复制和修改，然而，任何修改在
 完成之前，读取者仍旧不能看到它们，ConcurrentHashMap不会抛出ConcurrentModificationExection
    乐观锁
    只要你主要是从免锁容器中读取，那么它就会比其synchronzied对应物发展对应物快许多，因为获取和
 释放qimr开销被省掉了，如果需要向免锁容器中执行少量写入，那么情况仍旧如此，但是什么算少量，这
 是一个很有意思的问题，本节将命介绍有关的各种不同的条件下，这些容器在性能
 方面的差异的大致概念。

    我将从一个泛型框架着手，它专门助于在任何的容器上执行测试，包括各种Map在内的，
 其中泛型参数C表示容器的类型。
 我们向构造器提供了各种有关的测试的信息，参数标识符应该是自解释的，然后它会以调用runTest()方法
 repetitions次，runTest()将建一个contDownLatch(因此测试可以知道所有任何何时完成)初始体可视化
 docker，然后调用startReadersAndWriters，并等待它们全部完成
    每个Reader和Writer类者基于TestTask，它可以计划里其抽象的方法的执行时间，然后在一个synchronized
 块中调用putResults()去存储度量结果
    为了使用这个框架，其中你可以识别出模版方法设计模式，我们必须让想要测试可以知道所有的任何何时完成
 初始化容器，然后调用startReaderSandWriters()，并等待它们全部完成

    在ListTest中，Reader和Writer类执行针对List<Integer>的具体动作，在Reader.putResults()中
 ，duration被存在来起来，result也是一样，这样可以为防止这些计算被优化掉，startResultderAndWriters()被
 定义为创建和执行具体的Readers和Writers
    一旦创建了ListTest,它就必须被进一步继承，以覆盖containerInitializer()，从而可以创建和初始化具体的测试
 docker
    在main()中，你可以看到各种测试变体，它们具有不同的数量的读取者和写入者，由于存在对
 Testter.initMain(args)的调用，所以你可以使用命令行参数来改变测试变量
    默认行是为每个测试运行10次，这有助于稳定输出，而输出是可以变化 的，因为存在着诸如
 hotspot优化和垃圾别收这样的JVm活动，你看到的样本输出己经被编辑为只显示每个测试的最后一个迭代，众输出
 中可以看到，synchronized ArryaList无论读取者和写入者的数量是多少，都具有大致相同的性能，读取者
 与其他读取者竞争乐的方式与写入者相同，但是，CopyOnWriterArrayList在没有写入者时，速度会快许多
 ，并且在有5个写入者时，速度仍旧明显地快，看起来你应该尽量使用copyOnWriterArrayList，对列表写入的影响并没有超过短期同步整个列表的
 影响，当然你必须在你的具体应用中尝试这两种上不同的方式，以了解到到底哪个更好一些。
    再次注意，这还不是测试结果绝对不变的良好的工作基准测试，你的结果几乎肯定是不同的，
 这里的目标只是让你对两各不同类型的容器的相对行为有个概念上的认识
    因为CopyonWriteArraySet使用了COpyonWriterArrayList，所以它的行为与此类型，在这里
 就不需要另外设计一个单独的测试了
    比较各种map实现
    我们可以使用相同的sakl来得到synchroizedHashMap和ConcurrentHashMap在性能方面的比较结果
    向ConcurrentHashMap添加写入者的影响甚至还不如CopyonWriteArrayList明显，这是国为ConcrurretnHashMap
    使用了一种不同的技术，它可以明显地最小化写入所千万的影响


 * @param <C>
 */
public abstract class Tester<C> {
    static int testReps = 10;
    static int testCycles = 1000;
    static int containerSize = 1000;

    abstract C containerInitializer();

    abstract void startReadersAndWriters();

    C testContainer;
    String testId;
    int nReaders;
    int nWriters;
    volatile long readResult = 0;
    volatile long readTime = 0;
    volatile long writeTime = 0;
    CountDownLatch endLatch;
    static ExecutorService exec =
            Executors.newCachedThreadPool();
    Integer[] writeData;

    Tester(String testId, int nReaders, int nWriters) {
        this.testId = testId + " " +
                nReaders + "r " + nWriters + "w";
        this.nReaders = nReaders;
        this.nWriters = nWriters;
        writeData = Generated.array(Integer.class,
                new RandomGenerator.Integer(), containerSize);
        for (int i = 0; i < testReps; i++) {
            runTest();
            readTime = 0;
            writeTime = 0;
        }
    }

    void runTest() {
        endLatch = new CountDownLatch(nReaders + nWriters);
        testContainer = containerInitializer();
        startReadersAndWriters();
        try {
            endLatch.await();
        } catch (InterruptedException ex) {
            System.out.println("endLatch interrupted");
        }
        System.out.printf("%-27s %14d %14d\n",
                testId, readTime, writeTime);
        if (readTime != 0 && writeTime != 0)
            System.out.printf("%-27s %14d\n",
                    "readTime + writeTime =", readTime + writeTime);
    }

    abstract class TestTask implements Runnable {
        abstract void test();

        abstract void putResults();

        long duration;

        public void run() {
            long startTime = System.nanoTime();
            test();
            duration = System.nanoTime() - startTime;
            synchronized (Tester.this) {
                putResults();
            }
            endLatch.countDown();
        }
    }

    public static void initMain(String[] args) {
        if (args.length > 0)
            testReps = new Integer(args[0]);
        if (args.length > 1)
            testCycles = new Integer(args[1]);
        if (args.length > 2)
            containerSize = new Integer(args[2]);
        System.out.printf("%-27s %14s %14s\n",
                "Type", "Read time", "Write time");
    }
} ///:~
