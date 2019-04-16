package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/***
 *      请注意，在配合中可以被撤销的类不是Runnable,而所有的依赖于IntGenerator对象的
 *  EventChecker任务钭测试它，以查看它是否已经被撤销，正如你在run()所见，通过这种方式，
 *  共享公共的资源的任务可以观察该资源的终止信号，这可以少儿险所谓竞争条件，因此产生冲突或
 *  一致结果的情况，你必须仔细考虑
 *  并防范并发系统失败的所有可能途径，例如，一个任务不能依赖另一个任务，因为任务关闭
 *  的左右为难无法得到保证，这里，通过使任务依赖于非任务对象，我们可以消除潜在的竞争条件，
 *
 *  test()方法通过启动大量使用相同的IntGenerator的EvenChecker，设置并执行对任何类型的
 *  IntGenerator的测试，如果IntGenerator引发失败，那么test()将报告并返回，否则，你必须
 *  按下Control+C来终止它，
 *
 *      EventChecker任务总是读取和测试从与其相关的IntGentrator返回值，注意，如果Generator.isCanceled()为
 *      true,则run()将返回，这将告知EventChecker.test()中的Executor该任务完成了，任何EventChecker任务都
 *      可以在下期相关联的IntGenrator上调用cancel()，这将导致所有的其他使用该IntGenenetor的EvenChecker得体的
 *      关闭，在后面各节中，你将看到java包含的用于线程终止的各种更通用的机制
 *
 *
 *      我们看到的
 *
 */
public class EventChecker implements Runnable {
    private IntGenerator generator;

    private final int id;

    public EventChecker(IntGenerator g, int ident) {
        generator = g;
        id = ident;
    }

    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(val + "not even !");
                generator.cancel();
            }
        }
    }


    public static void test(IntGenerator gp, int count) {
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EventChecker(gp, i));
        }
        exec.shutdownNow();
    }


    public static void test(IntGenerator gp) {
        test(gp, 10);

    }


    public static void main(String[] args) {
        IntGenerator gp = new IntGenerator() {
            @Override
            public int next() {
                return new Random().nextInt(47);
            }
        };
        test(gp);
    }
}
