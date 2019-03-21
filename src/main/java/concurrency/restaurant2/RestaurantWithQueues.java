//: concurrency/restaurant2/RestaurantWithQueues.java
// {Args: 5}
package concurrency.restaurant2;

import enumerated.menu.Course;
import enumerated.menu.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;


/*****
 *
 *
 *  741页
 *      饭店仿真
 *      这个仿真添加了更多的仿真的组件，例如Order和Plate，从而充实了本章前面的描述的
 *  Restautrant.java示例，并且它重用了第19章中有menu类，它还引入了java SE5 的
 *  SynchrooutsQueue，这是一种没有内部容量的阻塞队列，因此每个put()都必须等待一个take()
 *  反之亦然 ，这就好像是你在把一个对象交给某人-没有任何桌子可以放置这个对象，因此只有
 *  在这个人伸出手，准备好接收这个对象时，你才能工作，在本例中，SynchronousQueue表示
 *  设置在用餐者面前的某个位置，以加强在任何时刻只能上一道菜的这个概念
 *      本例中剩下的类和功能都遵循Restautrant.java的结构，或者是对实际的饭店操作的相当直接的
 *  映射。
 *      关于这个示例，需要观察的一项非常重要的事项，就是使用队列在任务间通信所带来的管理
 *  复杂度，这个单项技术通过反转控制极大地简化了并发编程的过程，任务没有直接地互发送对象
 *  接收任务将处理对象，将其当作 一个消息来对待，而不是向
 *  它发送消息，如果只要可能遵循这项技术，那么你构建出健壮的并发系统的可能性会大大
 *  增加
 *      下面的仿真示例将本章的许多的概念都结合在一起了，考虑一个假想的用于汽车的机器人组装
 *  每辆Car都将分多个阶段构建，从创建底盘开始，紧跟安装发动机，车厢和轮子
 *      Car是经由CarQueue从一个地方传送到另一个地方的，CarQueue是一种LinkedBlockingQueue
 *  类型，ChassisBuilder创建了一个未加修饰的Car,并将Car放置到即将离开它的CarQueue中，然后
 *  被传送到下一个操作，最终的CarQueue的消费的是一个Reporter对象，它只是打印Car，以显示所有的
 *  任务都己经正确的完成了。
 *      Robot是在池中管理的，梁城要完成工作时，就会从池中雇请适当的Rotot，在工作完成时，
 *   这个Robot会返回到池中。
 *      在main()中创建了所有的必需的对象，并初始化了各个任务，最后启动的ClassBuild,从而
 *  启动整个过程，但是，由于LinkedBolckingQueue的行为，使得最先启动它也没有问题，注意
 *  这个我程序遵循了本章的描述的所有有关的对象和任务生命周期的设计原则，因此关闭这个过程将是
 *  安全的。
 *      你会注意到，Car将其所有的方法设置成了synchronized的，正如它所表现出来的那样，在
 * 本例中，这是多余的，因为在工厂的内部，Car是通过队列移动的，并且在任何时刻，只有一个
 * 任务能够在某辆车上工作，基本上，从队列可以强制串行化的访问Car，但是这正是你可能落入
 * 的陷阱，你可能会说， 让我们尝试阗通过不对的Car类同步来时先做化，因为看起来Car在这里
 * 并不需要同步，但是稍后，当这个系统连接到另一个需要Car被同步的系统时，它就会崩溃
 *      进行这样的声明会简单得多，Car可能会被多个线程使用，因此我们需要以明显的方式使
 *  其成为线程安全的，我把这种方式推荐给为在公园中，你会在陵水的走路上发现一些保护围拦
 * ，而是防止你跌落悬崖，但是不要倚靠围栏当然，这条规则的真实上的不是要阻止你借助围栏
 * ，而是防止你中东悬崖，但是不要你帮我围栏 与不要跌落悬崖 相比 是一条遵循起来
 * 要容易得多的规则
 *
 *
 */
// This is given to the waiter, who gives it to the chef:
class Order { // (A data-transfer object)
    private static int counter = 0;
    private final int id = counter++;
    private final Customer customer;
    private final WaitPerson waitPerson;
    private final Food food;

    public Order(Customer cust, WaitPerson wp, Food f) {
        customer = cust;
        waitPerson = wp;
        food = f;
    }

    public Food item() {
        return food;
    }

    public Customer getCustomer() {
        return customer;
    }

    public WaitPerson getWaitPerson() {
        return waitPerson;
    }

    public String toString() {
        return "Order: " + id + " item: " + food +
                " for: " + customer +
                " served by: " + waitPerson;
    }
}

// This is what comes back from the chef:
class Plate {
    private final Order order;
    private final Food food;

    public Plate(Order ord, Food f) {
        order = ord;
        food = f;
    }

    public Order getOrder() {
        return order;
    }

    public Food getFood() {
        return food;
    }

    public String toString() {
        return food.toString();
    }
}

class Customer implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final WaitPerson waitPerson;
    // Only one course at a time can be received:
    private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<Plate>();

    public Customer(WaitPerson w) {
        waitPerson = w;
    }

    public void
    deliver(Plate p) throws InterruptedException {
        // Only blocks if customer is still
        // eating the previous course:
        placeSetting.put(p);
    }

    public void run() {
        for (Course course : Course.values()) {
            Food food = course.randomSelection();
            try {
                waitPerson.placeOrder(this, food);
                // Blocks until course has been delivered:
                print(this + "eating " + placeSetting.take());
            } catch (InterruptedException e) {
                print(this + "waiting for " + course + " interrupted");
                break;
            }
        }
        print(this + "finished meal, leaving");
    }

    public String toString() {
        return "Customer " + id + " ";
    }
}

class WaitPerson implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Restaurant restaurant;
    BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<Plate>();

    public WaitPerson(Restaurant rest) {
        restaurant = rest;
    }

    public void placeOrder(Customer cust, Food food) {
        try {
            // Shouldn't actually block because this is
            // a LinkedBlockingQueue with no size limit:
            restaurant.orders.put(new Order(cust, this, food));
        } catch (InterruptedException e) {
            print(this + " placeOrder interrupted");
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until a course is ready
                Plate plate = filledOrders.take();
                print(this + "received " + plate +
                        " delivering to " +
                        plate.getOrder().getCustomer());
                plate.getOrder().getCustomer().deliver(plate);
            }
        } catch (InterruptedException e) {
            print(this + " interrupted");
        }
        print(this + " off duty");
    }

    public String toString() {
        return "WaitPerson " + id + " ";
    }
}

class Chef implements Runnable {


    private static int counter = 0;
    private final int id = counter++;
    private final Restaurant restaurant;
    private static Random rand = new Random(47);

    public Chef(Restaurant rest) {
        restaurant = rest;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until an order appears:
                Order order = restaurant.orders.take();
                Food requestedItem = order.item();
                // Time to prepare order:
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                Plate plate = new Plate(order, requestedItem);
                order.getWaitPerson().filledOrders.put(plate);
            }
        } catch (InterruptedException e) {
            print(this + " interrupted");
        }
        print(this + " off duty");
    }

    public String toString() {
        return "Chef " + id + " ";
    }
}

class Restaurant implements Runnable {


    private List<WaitPerson> waitPersons = new ArrayList<WaitPerson>();
    private List<Chef> chefs = new ArrayList<Chef>();
    private ExecutorService exec;
    private static Random rand = new Random(47);



    BlockingQueue<Order> orders = new LinkedBlockingQueue<Order>();

    public Restaurant(ExecutorService e, int nWaitPersons, int nChefs) {
        exec = e;
        for (int i = 0; i < nWaitPersons; i++) {
            WaitPerson waitPerson = new WaitPerson(this);
            waitPersons.add(waitPerson);
            exec.execute(waitPerson);
        }
        for (int i = 0; i < nChefs; i++) {
            Chef chef = new Chef(this);
            chefs.add(chef);
            exec.execute(chef);
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // A new customer arrives; assign a WaitPerson:
                WaitPerson wp = waitPersons.get(rand.nextInt(waitPersons.size()));
                Customer c = new Customer(wp);
                exec.execute(c);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            print("Restaurant interrupted");
        }
        print("Restaurant closing");
    }
}

public class RestaurantWithQueues {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();

        Restaurant restaurant = new Restaurant(exec, 10, 5);


        exec.execute(restaurant);


        if (args.length > 0) // Optional argument
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
            print("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}





/* Output: (Sample)
WaitPerson 0 received SPRING_ROLLS delivering to Customer 1
Customer 1 eating SPRING_ROLLS
WaitPerson 3 received SPRING_ROLLS delivering to Customer 0
Customer 0 eating SPRING_ROLLS
WaitPerson 0 received BURRITO delivering to Customer 1
Customer 1 eating BURRITO
WaitPerson 3 received SPRING_ROLLS delivering to Customer 2
Customer 2 eating SPRING_ROLLS
WaitPerson 1 received SOUP delivering to Customer 3
Customer 3 eating SOUP
WaitPerson 3 received VINDALOO delivering to Customer 0
Customer 0 eating VINDALOO
WaitPerson 0 received FRUIT delivering to Customer 1
...
*///:~
