//: concurrency/waxomatic/WaxOMatic.java
// Basic task cooperation.
package concurrency.waxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/*****
 *  702页
 *      线程之间的协作
 *      正如你所见到的，当你使用线程来运行多个任务时，可以通过使用锁（互斥）来同步
 *  两个任务的行为，从而使得一个任务不会干涉另一个任务的资源，也就是说，如果两个任务在
 *  交替着步入某项共享资源（通常是内存），你可以使用互斥来使得任何时刻只有一个任务可以访问
 *  这项资源
 *      这个问题已经解决了，下一步是学习如何使任务彼此之间可以协作，以使得多个任务可
 *  一起工作去解决某个问题，现在的问题不是彼此之间干涉，而是彼此之间的协调，因为在这类
 *  问题中，某些部分必须在其他部分解决之前解决，这非常像项目规划，必须先挖房子的地基，
 *  但是接下来的可以并行地铺设钢结构和构建水泥部件，而这两项任务必须在混凝土浇注之前
 *  完成，管道必须在水泥板必须在开始构筑房屋骨架之前到位，等等，
 *  在这些任务中，某些可以并行执行，但是某些步骤需要所有的任务都结束之后才能开动。
 *      当任务协作时，关键问题是这些任务之间的握手，为了实现这种握手，我们使用了相同的
 *  根除任何可能的竞争条件，在互斥之上，我们为任务添加了可以响应某个信号，这样就可以
 *  根除任何可能的竞争条件，在互斥之上，我们为任务添加了一种途径，可以将共自身挂起，直
 *  至某些某些条件发生变化（例如管道现在已经到位），表示是时候让这个任务向前开动了为止
 *  在本节，我们将浏览任务间的握手问题，这种握手可以通过Object的方法wait()和notify()来安全
 *  地实现，javaSE5 的并发类库还提供了具有await()和signal()方法的condition对象，我们将看到
 *  产生的各类问题，以及相应的解决方案。
 *
 *  21.5.1 wait()与notifyAll()
 *      wait()使你可以等待某个条件发生变化，而改变这个条件超出了当前方法的控制能力，通常
 * 这种条件将由另一个任务来改变，你上肯定不想在你的任务测试这个条件的同时，不断的进行空
 * 循环，这被称为忙等待，通常是一种不良的CPU周期使用方式，因此wait()会等待外部世界，产生
 * 变化的时候将任务挂起，并且只有在notify()或notifyAll()发生时，即表示发生了某些感兴趣的
 * 事物，这个任务才会被唤醒并去检查所以准备生的变化，因此，wait()提供了一种在任务之间对活动
 * 同步的方式。
 *      调用sleep()的时候锁并不有被释放，调用yiedld也属于这种情况，理解这一点很重要，另一
 * 方面，当一个任务在方法里遇到了对wait()的调用的时候，线程的执行被挂起，对象上的锁释放，
 * 因为wait()将释放锁，这就意味着另一个任务可以获得这个锁，因此在该对象（现在的是锁定的）
 * 中的其他synchronized方法可以在wait()期间被调用，这一点到头重要，因为这此其他的方法
 * 通常将产生改变，而这种改变正是使被挂起的任务重新唤醒所感兴趣的变化，因此，当你调用wait()
 * 时，就是在声明我己经刚刚做完能做的所有事情，因此我要在这里等待，但是我有希望其他的synchronized
 * 操作的条件适合的情况下能够执行
 *      有两种形式的wait()，第一种版本的接受毫秒数作为参数，含义与sleep()方法里参数的意思相同
 * 都是指在此期间暂停，但是与sleep()不同的是，对于wait()而言
 *      1）在wait()期间对象锁是释放的
 *      2）可以通过notiry()，notifyAll() ，或者令时间到期，从wait()中恢复执行
 *      3）可以通过notify()notifyAll() ,或者令时间到期，从wait()中恢复执行
 *      第二种，也是更常用形式的wait()不的接受任何参数，这种wait()将无限等待下去，直到线程接收到notify()或者notifyAll()
 *      消息
 *
 *      wait(),notify()以及notifyAll() 有一个比较特殊的方面，那就是这些方法是基类Object的一
 *  部分，而不是属于Thread的一部分，尽管开始看起来有点奇怪，仅仅针对线程的功能却作为
 *  通用基类的一部分而实现，不过这是有道理的，因为这些方法操作的锁也是所有对象的一部分
 *  所以，你可以把，wait()放进任何同步控制块里调用ait()，notify（） 和nofityAll()
 *  （因为不用操作锁，所以slee()可以在非同步控制方法里调用），如果在非同步控制方法里调用
 *  这些方法，程序能通过编译，但运行的时候，将得到IllegalMoitorStateExcetipon异常，并伴随
 *  着一些含糊的消息，比如当线程不是拥有者，消息的意思是，调用wait()notify()和
 *  nofityAll()的任务在调用这些方法前必须，拥有获取，对象的锁
 *      可以让另一个对象操作以维护其自己的锁，要这么做的话，必须首先得到对象的
 *  锁，比如，如果要向对象x发送，notifyAll()那么就必须 在能够取得x的锁同步控制块中这么做
 *  synchized(x){
 *      x.notifyAll()
 *  }
 *  让我们看一个简单的示例，WaxOMatic.java有两个过程，一个是将蜡涂到Car上，一个是
 *  抛光它，抛光任务在涂蜡任务完成之前，是不能执行其工作的，而涂蜡任务在兴另一层蜡之前
 *  必须 等待抛光任务完成，WaxOn和WaxOff者使用了Car对象，该对象在这些任务等待条件变化地
 *  时个，使用wait()和nofityAll()来挂起和重新启动这些任务。
 *      这里的car有一个单一的布尔属性，waxOn，表示，涂蜡抛光处理的状态，
    在wait()而被挂起，这个行为发生synchronized方法中这一点很重要，因为在这样的方法中，任务
 已经获得了锁，当你调用wait()时，线程被挂起，而锁被释放，锁被释放这一点是本质所在
 因为为了安全地改变对象的状态，例如，将waxOn改变为true,如果被挂起的任务要继续执行
 就必须 执行该动作，其他某个任务就必须能够获得这个锁，在本例中，如果另一个任务调用waxed()
 来表示，是时候该于点什么了，那么就必须 获得这个锁，从而将waxON改变为true,之
 后，waxed()调用notifyAll()，这将唤醒在对wait()的调用中被求挂起的任务，为了使设计公司任务从
 wait()中唤醒，它必须首先生新获得当它是进入wait()时释放的锁， 在这个锁变得可用之前，这个
 伤是不会被唤醒的
    WaxOn.run()表示给汽车打蜡过程第一个步骤，因此它将执行它的操作，调用sleep()以模拟需要涂蜡的
 时间，然后告知洗车涂蜡结束，并调用waitForBuffing()，这个方法会用一个wait()调用来挂起这个任务
 ，直至WaxOff伤调用这辆车的buffed()，从而改变状态并调用notify()为止，另一方面，waxoff.run()
 立即进入waitForWaxing()，并因此而被持起，直到WaxON没做完蜡并且waxed()被调用，在运行这个程序时
 你可以看到当控制权在两个线程，当你调用某个ExceutorService的shutdownNow()时，它会调用所有由
 它控制的线程的interrpt();
    前面的示例强调你必须 用一个检查一个感兴趣的条件的whitle的循环包围wait()，这很重要，因为
 你可能有多个任务出于相同的原因在等待同一个锁，而第一个唤醒任务可能会改变这种状况
 即使你没有这么做，有人也会通过继承你的类去这么做，如果属于这种情况，那么这个
 任务应该被再次挂起，直至其感兴趣的条件发生变化。
    在这个任务从其wait()中被唤醒的时刻，有可能会有某个其他操作已显得无关紧要，此时，应该通过
 再次调用，wait()来将其重新挂起。
    也有可能某些任务出于不同的原因在等待你的对象上的锁，在这种情况下必须作用
 nofityAll()在这种情况下，你需要检查是否己经由正确的原因唤醒，如果不是，就
 再一次调用wait()
    因此，其本质就是要检查所感兴趣的特定条件，并在条件不满足的情况下返回到wait()中，惯用的
 方法就是使用while来编写这种代码。
    错失的信号
    当两个线程使用notify()/wait() 或 wait()进行协作时，有可能错过某个信号，假设，
 T1 是通知T2线程，而这两个线程都是使用下面有缺陷的方式实现的。
    T1
    synchronized(shareMonitor){
    <setup confition for T2>
 }
    T2
    while(somecondition){
    synchronized(sharedMonitor){
        sharedMonitor.wait()

 }
 }

    <Setup condition for T2> 是防止T2调用wait()的一个动作，当然前提是T2还没有调用wait()
    假设T2对someCondition 求值并发现其为true,在Point1,线程调度器可能切换到T1,而T1将执行
    其设置，然后调用notify()，当t2得以继续执行时，此时对于T2来说，时机己经太晚了，
    以至于不能意识到这个条件己经发生了变化，因此会盲目是进入wait()此时，notify将错失，而
    T2也将无限的等待这个己经发生的过的信号，从而产生死锁
    synchroized(sharedMonitor){
        while(){
    sharemontor.wait()
 }
 }
    现在，如果T1首先执行，当控制返回t2时，它将发现条件发生了变化，从而不会进入wait()
    反过来，如果t2首先执行，那它将进入wait()，并且稍后会由f1唤醒，因此，信号，不会错失。
    nofity  notifyAll()

 1

 */
class Car {
    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        notifyAll();
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn == false)
            wait();
    }

    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (waxOn == true)
            wait();
    }
}

class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                printnb("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax On task");
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                printnb("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax Off task");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
} /* Output: (95% match)
Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Exiting via interrupt
Ending Wax On task
Exiting via interrupt
Ending Wax Off task
*///:~
