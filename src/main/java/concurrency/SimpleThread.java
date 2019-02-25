package concurrency;//: concurrency/SimpleThread.java
// Inheriting directly from the Thread class.


/****
 *
 *
 * 665
 * 编码的变体
 *      到目前为止，在你所看到的示例中，任务类都实现了Runnable，在非常简单的情况下，你可能会希望使用直接的Thread
 * 继承这种直接从Thread继承这种可替换的方式。就像下面的这样。
 *
 *
 * 就像下面这样
 *
 *
 *
 *
 *
 *
 *
 *
 ***/
public class SimpleThread extends Thread {
    private int countDown = 5;
    private static int threadCount = 0;

    public SimpleThread() {
        // Store the thread name:
        super(Integer.toString(++threadCount));
        start();
    }

    public String toString() {
        return "#" + getName() + "(" + countDown + "), ";
    }

    public void run() {
        while (true) {
            System.out.print(this);
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new SimpleThread();
    }
} /* Output:
#1(5), #1(4), #1(3), #1(2), #1(1), #2(5), #2(4), #2(3), #2(2), #2(1), #3(5), #3(4), #3(3), #3(2), #3(1), #4(5), #4(4), #4(3), #4(2), #4(1), #5(5), #5(4), #5(3), #5(2), #5(1),
*///:~
