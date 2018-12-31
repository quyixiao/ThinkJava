package concurrency;//: concurrency/IntGenerator.java


/****
 *      考虑下面的例子，其中一个任务产生偶数，而其他任务消费这些数字，这里，消费者任务的唯一工作就是检查偶数的有
 * 效性。
 *      首先，我们定义EventChecker，即消费者任务，因为它将在随后所有的示例中被复用，为了将EvenChecker与我们要
 * 试验的各种类型的生成器解耦，我们将创建一个名为IntGenerator的抽象类，它包含EvenChecker必须了解的必不可少的方法
 * ，即一个next()方法，和一个可以执行撤销的方法，这个类没有实现Generator接口，因为它必须产生一个int,而泛型不支持
 * 基本类型摊销的方法，这个类没有实现Generator接口，因为它必须产生一个int,而泛型不支持基本类型的参数
 *      IntGenerator 有一个cancel()方法，可能修改boolean类型的canceled标志的状态，还须一个isCanceled()方法
 * 可以它的原子性的，即诸如赋值和返值这样的简单操作在发生时没有中断的可能，因引你不会看到这个域处于执行的这些简单的
 * 操作的过程中的中间状态，为了保证可视性，caceled()的标志还是volatile的，你将在本章稍后学习原子性可定义性。
 *
 *
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract int next();

    // Allow this to be canceled:
    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
} ///:~
