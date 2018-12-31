//: concurrency/E24_ProducerConsumer.java
// {Args: 1 200}
/********************** Exercise 24 ***********************
 * Solve a single-producer, single-consumer problem using
 * wait() and notifyAll(). The producer must not overflow
 * the receiver’s buffer, which can happen if the producer
 632
 Thinking in Java, 4th Edition Annotated Solution Guide
 * is faster than the consumer. If the consumer is faster
 * than the producer, then it must not read the same data
 * more than once. Do not assume anything about the relative
 * speeds of the producer or consumer.
 *********************************************************/
package concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// A queue for solving flow-control problems.
class FlowQueue<T> {
    private Queue<T> queue = new LinkedList<T>();
    private int maxSize;

    public FlowQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void put(T v)
            throws InterruptedException {
        while (queue.size() >= maxSize)
            wait();
        queue.offer(v);
        System.out.println("++++++++++++++" +queue.size()  );
        //maxSize++;
        notifyAll();
    }

    public synchronized T get() throws InterruptedException {
        while (queue.isEmpty())
            wait();
        T returnVal = queue.poll();
        //maxSize--;
        notifyAll();
        return returnVal;
    }
}

class Item {
    private static int counter;
    private int id = counter++;

    private String name;


    public Item(String name){
        this.name = name;
    }

    public String toString() {
        return id+"";
    }
}

// Produces Item objects
class Producer implements Runnable {
    private int delay;
    private FlowQueue<Item> output;

    public Producer(FlowQueue<Item> output, int sleepTime) {
        this.output = output;
        delay = sleepTime;
    }

    public void run() {
        for (; ; ) {

            try {
                output.put(new Item("item "));
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

// Consumes any object
class Consumer implements Runnable {
    private int delay;
    private FlowQueue<?> input;
    private String name;

    public Consumer(FlowQueue<?> input, int sleepTime,String name) {
        this.input = input;
        delay = sleepTime;
        this.name = name;
    }

    public void run() {
        for (; ; ) {
            try {
                input.get();
                System.out.println(this.name + "----------------" );
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

public class E24_ProducerConsumer {
    public static void main(String[] args) throws Exception {
        int producerSleep = 300;
        int consumerSleep = 1000;
        FlowQueue<Item> fq = new FlowQueue<Item>(20);
        ExecutorService exec = Executors.newFixedThreadPool(4);
        exec.execute(new Producer(fq, producerSleep));
        exec.execute(new Consumer(fq, consumerSleep,"Consumer1"));
        exec.execute(new Consumer(fq, consumerSleep,"Consumer2"));
        exec.execute(new Consumer(fq, consumerSleep,"Consumer3"));
        TimeUnit.SECONDS.sleep(50);
        exec.shutdownNow();
    }
} 