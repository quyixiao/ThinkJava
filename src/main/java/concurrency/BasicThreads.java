package concurrency;

//: concurrency/BasicThreads.java
// The most basic use of the Thread class.


/****
 *      Thread 构造器只需要一个Runnable对象，调用Thread对象的start()方法为该线程执行必需的初始化操作，然后
 *  调用runnable的run()方法，以便在这个新的线程中启动这个任务，尽管start()看起来产生了一个对长期运行方法的调用，
 *  但是从输出中可以看到，start() 迅速地返回了，因为Waiting for LiftoOff消息在倒计时完成之前就出现了，实际上，你产生
 *  的是对LiftOff.run()的方法调用，并且这个方法还没有完成，但是因为LiftOff.run() 是由不同的线程执行的，因此你仍旧
 *  可执行main()线程中的其他的操作，这种能力并不局限于main()线程，任何张程都可以启动另一个线程，因此程序会同时运行
 *  两个方法，main()方法和LiftOff.run()方法是程序中与其他的线程，同时执行的代码。
 *      你可以很容易的去驱动更多的任务，下面，你可以看到所有的任务彼此之间是如何互相呼应的。
 *      输出说明不同的任务的执行被换进换出时混在了一起，这种交换由线程调度器自动控制，如果在你的机器上有多个处理器，线程
 *  调度器将会在这些处理器之间默默分发线程
 *      这个程序一次运行的结果可能与另一次运行的结果不同，因为线程高度机制是非确定性的，事实上，你可以看到，在某个版本的
 *  JDK与下个版本之间，这个简单的程序在输出会产生巨大的差异，例如，较早的jdk不会频繁对时间切片，因此线程1可能首先循环到
 *  尽头，然后线程2会经历其所有的循环，等等，这实际上与调用一个例程去同时执行所有的循环到尽头，然后线程看起来会获得更加正
 *  规的服务，通常，sun并为提及这些种类的JDK的行为变化，因此你不能依赖于任何线程行为的一致性，最好的方式是在编写使用线程的
 *  代码时，尽可能的保守。
 *      当main()创建Thread对象时，它并没有捕获任何对这些对象的引用，在使用普通对象时，这对于垃圾回收来说是一场公平的游戏
 *  但是在使用Thread时，情况就不同了，每个Thread都注册自己的，因此确实有一个对它的引用，而且在它们的任务退出其run()并
 *  死亡之前，垃圾回收器无法清除他们，你可以从输出中看到，这些任务确实运行到了结束，因此，一个线程会创建一个单独的执行的线程
 *  ，在对start()的调用完成之后，它仍旧会继续的存在。
 *
 *
 *
 *
 *
 *
 */
public class BasicThreads {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff");
    }
} /*
     * Output: (90% match) Waiting for LiftOff #0(9), #0(8), #0(7), #0(6),
	 * #0(5), #0(4), #0(3), #0(2), #0(1), #0(Liftoff!),
	 */// :~
