package concurrency;//: concurrency/Restaurant.java
// The producer-consumer approach to task cooperation.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/*****
 * 709页
 *  生产者与消费者
 *    请考虑这样一个饭店，它有一个厨师和一个服务员，这个服务员必须等待厨师准备的膳食，
 * 当厨师准备好时，他会通知服务员，之后服务员上菜，然后返回继续等待，这是一个任务协作的示例，
 * 厨师代表着生产者，而服务员代表着消费者，两个任务必须在膳食被生产和消费时进行握手，而系统必须
 * 以有序的方式关闭，下面是对这个叙述建模的代码。
 *
 * 710页
 *      Restaurant是WaitPerson和chef的焦点，他们都必须知道在为哪个Restautrat工作，因为他
 *  们必须和这家饭店的餐窗的打交道，以便放置或拿取膳食restautran.meal,在run中，WaitPerson
 *  进入wait模式，停止其任务，直至被chef的notifyAll()唤醒，由于这是一个非常简单的程序，
 *  因此我们知道只有一个任务将在WaitPerson而不是notifyAll()，但是，在更复杂的情况下，可能会有
 *  多个任务在某个特定对象锁上等待，因此你不知道哪个任务应该被唤醒，因此，调用nofityAll()
 *  要更安全一此，这样可以唤醒等待这个锁的所有的任务，而每个任务都必须决定这个通知是否与
 *  自己相关。
 *      一旦Chef送上meal并通过知Chef送上Meal，这个Chef就将等待，直至WaitPerson收集到订单并
 *  通知Chef,之后Chef就可以烧下一份Meal了。
 *      注意，wait()被包装在下一个while()语句中，这个语句在不断地测试正在等待的事物，乍看上
 *  去这有点怪-如果在等待一个订单，一旦你唤醒，这个订单就必定是可获得的，对吗？正
 *  前面的注意到的，问题是在并发应用中，某个其他的任务可能会在WaitPerson被唤醒时，会突然
 *  插足并拿走订单，唯一安全的方式是使用下面的这种wait()的惯用法（当然要在恰当的同步内部，并采用
 *  防止错失信号，可能性的程序设计）
 *      while(conditionIsNotMet){
 *            wait();
 *      }
 *      这可以保证在你退出等待循环之前，条件将得到满足，并且如果你收到了事物的通知，
 *  而它与这个条件并无关系，（就象在使用notifyAll()时可能发生的情况一样），或者在你完全
 *  退出等待循环之前，这个条件发生了变化，都可以确保你可以重返等待状态。
 *      请注意观察，对nofityAll()的调用必须首先捕获waitPerson上锁，而在WaitPerson.run()中。
 *  的对wait()的调用会自动地释放这个锁，因此这是有可能实现的，因为调用notifyAll()必然拥有
 *  这个锁，所以这个可以保证两个试图在同一个对象上调用notifyAll()的任务不会互相冲突。
 *      通过把整个run()方法体放到一个try语句块中，可使得这两个run()返回，并且通常这就是
 *  你应该做的，但是，以这种方式执行还有一些更有趣的东西，记住，shutdownNow()将向所有的
 *  由ExecutorService启动的任务发送interrupt()，但是在Chef()中，任务并没有获得该interrput()
 *  之后立即关闭，因为当任务试图是进入一个，可中断的，阻塞操作时，这个中断只能抛出
 *  interrputedException，因此，你将看到首先显示了，Order up! 然后当Chef试图调用sleep()时
 *  抛出了InterruptedExetion,如果移除对sleep()的调用，那么这个任务将回到run()循环的顶部，
 *  并由于Thread.interrupted()测试而退出，同时并不抛出异常。
 *      在前面的示例中，对于一个任务而言，只有一个单一的地点用于存放对象，从而使得另一个任务
 *  稍后可以使用对象，但是，在典型的生产者，消费者实现中，应使用先进先出队列来，
 *  存储被生产和消费对象，你将在本章稍后学习有关这种部队列的知识。
 *
 *
 *
 */
class Meal {
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    public String toString() {
        return "Meal " + orderNum;
    }
}

class WaitPerson implements Runnable {
    private Restaurant restaurant;

    public WaitPerson(Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null)
                        wait(); // ... for the chef to produce a meal
                }
                print("Waitperson got " + restaurant.meal);
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // Ready for another
                }
            }
        } catch (InterruptedException e) {
            print("WaitPerson interrupted");
        }
    }
}

class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;

    public Chef(Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null)
                        wait(); // ... for the meal to be taken
                }
                if (++count == 10) {
                    print("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                printnb("Order up! ");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            print("Chef interrupted");
        }
    }
}

public class Restaurant {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);

    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}






/* Output:
Order up! Waitperson got Meal 1
Order up! Waitperson got Meal 2
Order up! Waitperson got Meal 3
Order up! Waitperson got Meal 4
Order up! Waitperson got Meal 5
Order up! Waitperson got Meal 6
Order up! Waitperson got Meal 7
Order up! Waitperson got Meal 8
Order up! Waitperson got Meal 9
Out of food, closing
WaitPerson interrupted
Order up! Chef interrupted
*///:~
