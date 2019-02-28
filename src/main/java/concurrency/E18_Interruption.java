//: concurrency/E18_Interruption.java
/****************** Exercise 18 ************************
 * Create a non-task class with a method that calls
 * sleep() for a long interval. Create a task that calls
 * the method in the non-task class. In main(), start the
 * task, then call interrupt() to terminate it. Make sure
 * that the task shuts down safely.
 ******************************************************/
package concurrency;

import java.util.concurrent.TimeUnit;


/******
 *
 *
 *
 *
 * 699
 *
 *
 *
 * 创建一个非任务类，它有一个用较长的时间间隔调用sleep()的方法，创建一个
 * 任务，它将调用这个非任务类上的那个方法，在main()中，启动该任务，然后调用interrupt()
 * 来终止它，请确保这个任务被,它将调用这个非任务类上的那个方法，在main()中，启动该任务，然后调用interrupt()
 * 来终止它，请确保这个任务被安全的关闭
 *
 *
 *
 * 1
 *
 *
 *
 */


class NonTask {
    static void longMethod() throws InterruptedException {
        TimeUnit.SECONDS.sleep(60);  // Waits one minute
    }
}

class Task11 implements Runnable {
    public void run() {
        try {
            NonTask.longMethod();
        } catch (InterruptedException ie) {
            System.out.println(ie.toString());
        } finally {
            // Any cleanup code here...
        }
    }
}

public class E18_Interruption {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Task11());
        t.start();




        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
    }
}