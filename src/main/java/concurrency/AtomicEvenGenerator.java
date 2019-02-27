package concurrency;

//: concurrency/AtomicEvenGenerator.java
// Atomic classes are occasionally useful in regular code.
// {RunByHand}

import java.util.concurrent.atomic.AtomicInteger;


/*****
 *
 * 685
 *
 *
 *
 *
 *
 *      这里我们通过使用Atomicintege而消除了synchronized关键字，因为这个程序不会失败，所以添加一个Timer，以便在5
 * 秒钟之后自动的终止
 *      所以其他形式的同步再次通过使用AtomicInteger得到了根除
 * 应该强调的是，atomic类被设计用来构建java.uit.concurrent中的类，因此只有在特殊情况下才在自己的代码中使用它们，
 * 即便使用了它们也土地成本发确保不存在其他的可能出现的问题，通常依赖于锁要更安全一些，要么是synchronized关键字
 * ，要么是显式的Lock对象
 *
 *
 * 1
 *
 */
public class AtomicEvenGenerator extends IntGenerator {
    private AtomicInteger currentEvenValue = new AtomicInteger(0);

    public int next() {
        return currentEvenValue.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicEvenGenerator());
    }
} ///:~
