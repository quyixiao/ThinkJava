package concurrency;
//: concurrency/AtomicityTest.java

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/****
 *
 *
 * 682
 *
 *
 *
 * 如果 你盲目的应用原子性的概念，那么就会看到在下面程序中的getValue()符合上面的描述。
 * 但是，该程序将找到奇数值并终止，尽管return i 确实是原子性的操作，但是缺少同步使得其数值可以在牌不稳定的
 * 中间状态时被读取，除此之外，由于i也不是volatitle的，因此还存在可视性问题，getValue()和evenIncremtn()
 * 必须是synchronized的，在诸如此类情况下，只有并发专家才有能力进行优化，而你还是应该运用Brian的同步规则
 *
 *
 *  这地有一个利用操作系统进程简单示例，在编写本书时，我会有规律的创建本书的当前状态的多个冗余备份副本
 *
 *  这是并发的理想示例，每个任务都作为进程在其自己的地址宣传部中执行，因此你舔得国之间根本不可能干涉,
 *  操作系统会处理确保文件不具有复制的所有的细节，因此，不会有任何风险，你可以获得更快的程序，并且完全免费
 *
 *
 *  有些人走得更远，提倡将进程作为唯一合理的并发方式，但遗憾的是，对进程通常会有数量的限制，以避免它们在不同的并发系统之间的
 *  可应用性
 *
 *
 *
 * sun
 *
 *
 *  *
 *
 * 但是，该程序将找到奇数值并终止，以文害辞
 *
 *
 *
 * 1
 *
 *
 * hh瞿贻晓*
 */
public class AtomicityTest implements Runnable {
    private int i = 0;

    public int getValue() {
        return i;
    }

    private synchronized void evenIncrement() {
        i++;
        i++;
    }

    public void run() {
        while (true)
            evenIncrement();
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        exec.execute(at);
        while (true) {
            int val = at.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
} /* Output: (Sample)
191583767
*///:~
