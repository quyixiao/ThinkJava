package concurrency;

import net.mindview.util.Print;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.*;

public class ADaemon implements Runnable {
    public void run() {

        try {
            print("Starting ADaemon");

            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {


          print("Exiting via Interruptieexception ");
        }finally {
            print("this should always run ?");
        }
    }



}
