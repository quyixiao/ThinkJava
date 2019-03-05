//: concurrency/E30_SendReceive.java
/********************** Exercise 30 ***********************
 * Modify PipedIO.java to use a BlockingQueue instead of
 * a pipe.
 *********************************************************/
package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/****
 *
 *
 *
 * 718
 *
 * 修改pipeldIO.java ，使其使用BlockingQueue而不是管道
 *
 *
 *
 *
 *
 *
 * 1
 *
 *
 *
 */
class CharQueue extends LinkedBlockingQueue<Character> {



}

class Sender22 implements Runnable {
    private Random rand = new Random(47);
    private CharQueue out = new CharQueue();

    public CharQueue getQueue() {
        return out;
    }

    public void run() {
        try {
            while (true)
                for (char c = 'A'; c <= 'z'; c++) {
                    out.put(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
        } catch (InterruptedException e) {
            print(e + " Sender interrupted");
        }
    }
}

class Receiver22 implements Runnable {
    private CharQueue in;

    public Receiver22(Sender22 sender) {
        in = sender.getQueue();
    }

    public void run() {
        try {
            while (true) {
                // Blocks until characters are there:
                printnb("Read: " + in.take() + ", ");
            }
        } catch (InterruptedException e) {
            print(e + " Reader interrupted");
        }
    }
}

public class E30_SendReceive {
    public static void main(String[] args) throws Exception {
        Sender22 sender = new Sender22();
        Receiver22 receiver = new Receiver22(sender);
        ExecutorService exec = Executors.newCachedThreadPool();




        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }
}