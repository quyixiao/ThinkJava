//: concurrency/E01_Runnable.java
/****************** Exercise 1 *****************
 * Implement a Runnable. Inside run(), print a
 * message, and then call yield(). Repeat this
 * three times, and then return from run(). Put
 * a startup message in the constructor and a
 * shutdown message when the task terminates. Create
 * a number of these tasks and drive them using
 * threads.
 ***********************************************/
package concurrency;


/*

 656


 * 实现一个Runnable，在run（）内部打印一个消息，然后调用yield（）,重复这个操作三次，
 * 然后从run()中返回，在构造器中放置一条启动消息，并且放置一条在任务终止时的关闭消息
 * ，使用线程创建大量的这种任务并驱动 它们
 *   1
 *
 *
 *
 *
 *
 */
class Printer implements Runnable {
    private static int taskCount;
    private final int id = taskCount++;

    Printer() {
        System.out.println("Printer started, ID = " + id);
    }

    public void run() {
        System.out.println("Stage 1..., ID = " + id);
        Thread.yield();
        System.out.println("Stage 2..., ID = " + id);
        Thread.yield();
        System.out.println("Stage 3..., ID = " + id);
        Thread.yield();
        System.out.println("Printer ended, ID = " + id);
    }
}

public class E01_Runnable {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new Thread(new Printer()).start();
    }
}