package concurrency;//: concurrency/CloseResource.java
// Interrupting a blocked task by
// closing the underlying resource.
// {RunByHand}

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;


/****
 *
 *
 * 694页
 *
 * 对于这类问题，有一个略显笨拙但是有时确实行之有效的解决方案，即关闭任务在其上发生阻塞的底层
 *  资源
 *
 *
 *      在shutdownNow()被调用之后以及在两个输入流上调用close()之前的延迟强调的是一旦底层
 *  资源被关闭，傻将解除阻塞，请注意，有一点很有趣，interrupt()看起来发生在关闭Socket而
 *  不是关闭System.in
 *
 *
 *
 *  1
 *
 */
public class CloseResource {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);


        InputStream socketInput = new Socket("localhost", 8080).getInputStream();



        exec.execute(new IOBlocked(socketInput));
        exec.execute(new IOBlocked(System.in));
        TimeUnit.MILLISECONDS.sleep(100);
        print("Shutting down all threads");



        exec.shutdownNow();
        TimeUnit.SECONDS.sleep(1);
        print("Closing " + socketInput.getClass().getName());
        socketInput.close(); // Releases blocked thread
        TimeUnit.SECONDS.sleep(1);
        print("Closing " + System.in.getClass().getName());
        System.in.close(); // Releases blocked thread
    }
}


/* Output: (85% match)
Waiting for read():
Waiting for read():
Shutting down all threads
Closing java.net.SocketInputStream
Interrupted from blocked I/O
Exiting IOBlocked.run()
Closing java.io.BufferedInputStream
Exiting IOBlocked.run()
*///:~
