
//: concurrency/restaurant2/E36_RestaurantWithQueues2.java
// {Args: 5}
/******************** Exercise 36 ************************
 * Modify RestaurantWithQueues.java so there’s one
 * OrderTicket55 object per table. Change order to
 * orderTicket, and add a Table55 class, with multiple
 * Customers per table.
 *********************************************************/
package concurrency;

import enumerated.menu.Course;
import enumerated.menu.Food;

import java.util.*;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;

// This is consisted of many orders, and there's one ticket
// per table:
class OrderTicket55 {
    private static int counter;
    private final int id = counter++;
    private final Table55 table;
    private final List<Order55> orders =
            Collections.synchronizedList(new LinkedList<Order55>());

    public OrderTicket55(Table55 table) {
        this.table = table;
    }

    public WaitPerson55 getWaitPerson() {
        return table.getWaitPerson();
    }

    public void placeOrder(Customer55 cust, Food food) {
        Order55 order = new Order55(cust, food);
        orders.add(order);
        order.setOrderTicket(this);
    }

    public List<Order55> getOrders() {
        return orders;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(
                "Order55 Ticket: " + id + " for: " + table + "\n");
        synchronized (orders) {
            for (Order55 order : orders)
                sb.append(order.toString() + "\n");
        }
        // Prune away the last added 'new-line' character
        return sb.substring(0, sb.length() - 1).toString();
    }
}

class Table55 implements Runnable {
    private static int counter;
    private final int id = counter++;
    private final WaitPerson55 waitPerson;
    private final List<Customer55> customers;
    private final OrderTicket55 orderTicket =
            new OrderTicket55(this);
    private final CyclicBarrier barrier;
    private final int nCustomers;
    private final ExecutorService exec;

    public Table55(WaitPerson55 waitPerson, int nCustomers,
                   ExecutorService e) {
        this.waitPerson = waitPerson;
        customers = Collections.synchronizedList(
                new LinkedList<Customer55>());
        this.nCustomers = nCustomers;
        exec = e;
        barrier = new CyclicBarrier(nCustomers + 1,
                new Runnable() {
                    public void run() {
                        print(orderTicket.toString());
                    }
                });
    }

    public WaitPerson55 getWaitPerson() {
        return waitPerson;
    }

    public void placeOrder(Customer55 cust, Food food) {
        orderTicket.placeOrder(cust, food);
    }

    public void run() {
        Customer55 customer;
        for (int i = 0; i < nCustomers; i++) {
            customers.add(customer = new Customer55(this, barrier));
            exec.execute(customer);

        }
        try {
            barrier.await();
        } catch (InterruptedException ie) {
            print(this + " interrupted");
            return;
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        waitPerson.placeOrderTicket(orderTicket);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(
                "Table55: " + id + " served by: " + waitPerson + "\n");
        synchronized (customers) {
            for (Customer55 customer : customers)
                sb.append(customer.toString() + "\n");
        }
        return sb.substring(0, sb.length() - 1).toString();
    }
}

// This is part of an order ticket (given to the chef):
class Order55 {
    private static int counter;
    private final int id;
    private volatile OrderTicket55 orderTicket;
    private final Customer55 customer;
    private final Food food;

    public Order55(Customer55 cust, Food f) {
        customer = cust;
        food = f;
        synchronized (Order55.class) {
            id = counter++;
        }
    }

    void setOrderTicket(OrderTicket55 orderTicket) {
        this.orderTicket = orderTicket;
    }

    public OrderTicket55 getOrderTicket() {
        return orderTicket;
    }

    public Food item() {
        return food;
    }

    public Customer55 getCustomer() {
        return customer;
    }

    public String toString() {
        return "Order55: " + id + " item: " + food +
                " for: " + customer;
    }
}

// This is what comes back from the chef:
class Plate55 {
    private final Order55 order;
    private final Food food;

    public Plate55(Order55 ord, Food f) {
        order = ord;
        food = f;
    }

    public Order55 getOrder() {
        return order;
    }

    public Food getFood() {
        return food;
    }

    public String toString() {
        return food.toString();
    }
}

class Customer55 implements Runnable {
    private static int counter;
    private final int id;
    private final CyclicBarrier barrier;
    private final Table55 table;
    private int nPlates;  // Number of plates ordered

    public Customer55(Table55 table, CyclicBarrier barrier) {
        this.table = table;
        this.barrier = barrier;
        synchronized (Customer55.class) {
            id = counter++;
        }
    }

    // Only one course at a time can be received:
    private final SynchronousQueue<Plate55> placeSetting =
            new SynchronousQueue<Plate55>();

    public void
    deliver(Plate55 p) throws InterruptedException {
        // Only blocks if customer is still
        // eating the previous course:
        placeSetting.put(p);
    }

    public void run() {
        // First place an order:
        for (Course course : Course.values()) {
            Food food = course.randomSelection();
            table.placeOrder(this, food);
            ++nPlates;
        }
        try {
            barrier.await();
        } catch (InterruptedException ie) {
            print(this + " interrupted while ordering meal");
            return;
        } catch (BrokenBarrierException e) {

            throw new RuntimeException(e);
        }
        // Now wait for each ordered plate:
        for (int i = 0; i < nPlates; i++)
            try {
                // Blocks until course has been delivered:
                print(this + "eating " + placeSetting.take());
            } catch (InterruptedException e) {
                print(this + "waiting for meal interrupted");
                return;
            }
        print(this + "finished meal, leaving");
    }

    public String toString() {
        return "Customer55 " + id + " ";
    }
}

class WaitPerson55 implements Runnable {
    private static int counter;
    private final int id = counter++;
    private final Restaurant55 restaurant;
    final BlockingQueue<Plate55> filledOrders =
            new LinkedBlockingQueue<Plate55>();

    public WaitPerson55(Restaurant55 rest) {
        restaurant = rest;
    }

    public void placeOrderTicket(OrderTicket55 orderTicket) {
        try {
            // Shouldn't actually block because this is
            // a LinkedBlockingQueue with no size limit:
            restaurant.orderTickets.put(orderTicket);
        } catch (InterruptedException e) {
            print(this + " placeOrderTicket interrupted");
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until a course is ready
                Plate55 plate = filledOrders.take();
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
        return "WaitPerson55 " + id + " ";
    }
}

class Chef55 implements Runnable {
    private static int counter;
    private final int id = counter++;
    private final Restaurant55 restaurant;
    private static Random rand = new Random(47);

    public Chef55(Restaurant55 rest) {
        restaurant = rest;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until an order ticket appears:
                OrderTicket55 orderTicket =
                        restaurant.orderTickets.take();
                List<Order55> orders = orderTicket.getOrders();
                synchronized (orders) {
                    for (Order55 order : orders) {
                        Food requestedItem = order.item();
                        // Time to prepare order:
                        TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                        Plate55 plate = new Plate55(order, requestedItem);
                        order.getOrderTicket().getWaitPerson().
                                filledOrders.put(plate);
                    }
                }
            }
        } catch (InterruptedException e) {
            print(this + " interrupted");
        }
        print(this + " off duty");
    }

    public String toString() {
        return "Chef55 " + id + " ";
    }
}

class Restaurant55 implements Runnable {
    private List<WaitPerson55> waitPersons =
            new ArrayList<WaitPerson55>();
    private List<Chef55> chefs = new ArrayList<Chef55>();
    private ExecutorService exec;
    private static Random rand = new Random(47);
    final BlockingQueue<OrderTicket55> orderTickets =
            new LinkedBlockingQueue<OrderTicket55>();

    public Restaurant55(ExecutorService e, int nWaitPersons,
                        int nChefs) {
        exec = e;
        for (int i = 0; i < nWaitPersons; i++) {
            WaitPerson55 waitPerson = new WaitPerson55(this);
            waitPersons.add(waitPerson);
            exec.execute(waitPerson);
        }
        for (int i = 0; i < nChefs; i++) {
            Chef55 chef = new Chef55(this);
            chefs.add(chef);
            exec.execute(chef);
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // A new group of customers arrive; assign a
                // WaitPerson55:
                WaitPerson55 wp = waitPersons.get(
                        rand.nextInt(waitPersons.size()));
                int nCustomers = rand.nextInt(4) + 1;
                Table55 t = new Table55(wp, nCustomers, exec);
                exec.execute(t);
                TimeUnit.MILLISECONDS.sleep(400 * nCustomers);
            }
        } catch (InterruptedException e) {
            print("Restaurant55 interrupted");
        }
        print("Restaurant55 closing");
    }
}

public class E36_RestaurantWithQueues2 {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        Restaurant55 restaurant = new Restaurant55(exec, 5, 2);
        exec.execute(restaurant);
        if (args.length > 0) // Optional argument
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
            print("Press 'ENTER' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
} 