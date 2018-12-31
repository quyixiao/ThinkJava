package holding;//: holding/QueueDemo.java
// Upcasting to a Queue from a LinkedList.

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/***
 * Linked提供了方法以支持队列的行为，并且实现的Queue接口，因此LinkedList可以作为Queue的一种实现
 * ，通过将linkedList身上转型为Queue，下面的示例使用了Queue接口中的与Queue相关的方法
 *
 *      注意，与Queue相关的方法提供了完整的而独立的功能，即，对于Queue所继承的Collectio，在不需要使用它的
 * 任何情况下什么歌方法的情况下，就可以拥有一个可用的Queue
 *
 *
 *
 *
 * 237
 *
 * 1
 *
 */
public class QueueDemo {
    public static void printQ(Queue queue) {
        while (queue.peek() != null)
            System.out.print(queue.remove() + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        Random rand = new Random(47);
        for (int i = 0; i < 10; i++)
            queue.offer(rand.nextInt(i + 10));
        printQ(queue);
        Queue<Character> qc = new LinkedList<Character>();
        for (char c : "Brontosaurus".toCharArray())
            qc.offer(c);
        printQ(qc);
    }
} /* Output:
8 1 1 1 5 14 3 1 0 1
B r o n t o s a u r u s
*///:~
