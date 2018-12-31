package concurrency;//: concurrency/Fat.java
// Objects that are expensive to create.


/*****
 *
 *  734 页
 *      我们在池中管理这些对象，以限制这个构造器所造成的影响，我们可以创建一个任务，它
 *  将签出Fat对象，持有一段时间之后再将它们签入，以此来测试Pool这个类。
 *      在main中，创建了一个持有Fat对象的Pool，而这一组CheckoutTask则开始操作这个Pool。
 *  然后，main()线程签出池中Fat对象，但是并不签入它们，一旦池中所有的对象都被签出
 *  Semaphore将不再允许执行任何签出操作，Blocked的run()方法因此会被阻塞，2秒钟后，
 *  cancel()方法被调用，以此来挣脱Futrue的束缚，注意，额外信息的签入将被Pool忽略。
 *      这个示例依赖于Pool的客户端严格的并愿意签入所持有的对象，当其工作时，这是最简单的
 *  解决方案，如果你无法总是可以依赖于此，深入探讨了己经签出对象池的对象的管理方式。
 *
 */
public class Fat {
    private volatile double d; // Prevent optimization
    private static int counter = 0;
    private final int id = counter++;

    public Fat() {
        // Expensive, interruptible operation:
        for (int i = 1; i < 10000; i++) {
            d += (Math.PI + Math.E) / (double) i;
        }
    }

    public void operation() {
        System.out.println(this);
    }

    public String toString() {
        return "Fat id: " + id;
    }
} ///:~
