package concurrency;//: concurrency/SerialNumberChecker.java
// Operations that may seem safe are not,
// when threads are present.
// {Args: 4}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/******
 *
 *
 * 683页
 *    为了测试ServiaNmberGenato，我们需要不会耗尽内存的集（Set），以防需要花费很长时间来探测问题，这里所示的CircularSeet我不用的存储int数值的
 * 内存，并假设在你生成序列数时，产生数值覆盖冲突的可能性极小，add()和contains()方法都是synchronized,以防止线程冲突。
 *
 *      SerialNumberChecker包含一个静态的CircularSEt，它后面有所产生的所有序列数，另外还一个内嵌的SerialChccker类，它可以确保序列数是唯一的
 * ，通过创建多个任务来竞争竞争序列数，你将发现这些任务最终会得到重复的序列数，如果你运行的时间足够长的话，为了解决这个问题，在必须给我SerialNumber()
 * 前面添加了sychronized关键字。
 *      对于基本类型的读取和赋值操作被认为是安全的原子性操作，但是，正如你在AoomicityTest.java 中看到的，当对象牌不稳定状态时，仍旧很有可能使用原子性
 * 操作来访问他们，对这个问题作出假设是棘手而危险的，最明智的做法就是遵循Brian的同步规则。
 *
 *
 *  1
 *
 *
 */
// Reuses storage so we don't run out of memory:
class CircularSet {
    private int[] array;
    private int len;
    private int index = 0;

    public CircularSet(int size) {
        array = new int[size];
        len = size;
        // Initialize to a value not produced
        // by the SerialNumberGenerator:
        for (int i = 0; i < size; i++)
            array[i] = -1;
    }

    public synchronized void add(int i) {
        array[index] = i;
        // Wrap index and write over old elements:
        index = ++index % len;
    }

    public synchronized boolean contains(int val) {
        for (int i = 0; i < len; i++)
            if (array[i] == val) return true;
        return false;
    }
}


public class SerialNumberChecker {
    private static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static class SerialChecker implements Runnable {
        public void run() {
            while (true) {
                int serial = SerialNumberGenerator.nextSerialNumber();
                if (serials.contains(serial)) {
                    System.out.println("Duplicate: " + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < SIZE; i++)
            exec.execute(new SerialChecker());
        // Stop after n seconds if there's an argument:
        if (args.length > 0) {
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
            System.out.println("No duplicates detected");
            System.exit(0);
        }
    }
} /* Output: (Sample)
Duplicate: 8468656
*///:~
