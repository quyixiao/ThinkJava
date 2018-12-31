package concurrency;
//: concurrency/AtomicityTest.java

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/****
 * 如果 你盲目的应用原子性的概念，那么就会看到在下面程序中的getValue()符合上面的描述。
 * 但是，该程序将找到奇数值并终止，尽管return i 确实是原子性的操作，但是缺少同步使得其数值可以在牌不稳定的
 * 中间状态时被读取，除此之外，由于i也不是volatitle的，因此还存在可视性问题，getValue()和evenIncremtn()
 * 必须是synchronized的，在诸如此类情况下，只有并发专家才有能力进行优化，而你还是应该运用Brian的同步规则
 *
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
