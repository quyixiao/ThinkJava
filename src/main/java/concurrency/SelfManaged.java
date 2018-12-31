package concurrency;//: concurrency/SelfManaged.java
// A Runnable containing its own driver Thread.


/*****
 *
 *
 * 666 页
 *   你可以通过调用适当的Thread构造器为Thred对象赋予具体的名称，这个名称可以getName（）从toString()中获得。
 *   另一种可能会版的惯用法是自管理Runable接口
 *      这与从Thread继承并没有什么的特别的差异，只是语法稍微的晦涩一些但是，实现接口使得你会以继承另一个不同的类
 *   从而Thread继承将不行
 *
 *
 */
public class SelfManaged implements Runnable {
    private int countDown = 5;
    private Thread t = new Thread(this);

    public SelfManaged() {
        t.start();
    }

    public String toString() {
        return Thread.currentThread().getName() +
                "(" + countDown + "), ";
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
            new SelfManaged();
    }
} /* Output:
Thread-0(5), Thread-0(4), Thread-0(3), Thread-0(2), Thread-0(1), Thread-1(5), Thread-1(4), Thread-1(3), Thread-1(2), Thread-1(1), Thread-2(5), Thread-2(4), Thread-2(3), Thread-2(2), Thread-2(1), Thread-3(5), Thread-3(4), Thread-3(3), Thread-3(2), Thread-3(1), Thread-4(5), Thread-4(4), Thread-4(3), Thread-4(2), Thread-4(1),
*///:~
