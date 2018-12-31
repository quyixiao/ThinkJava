package holding;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/*****
 *
 *
 * 238页
 *  用由java.util.Random创建的Double值填充一个ProorityQueue用offer()方法
 *  然后使用poll()移除并显示它们
 *
 */
public class E28_PriorityQueue {
    static Random rand = new Random(47);

    public static void printQ(Queue<?> queue) {
        for (Object data = queue.poll(); data != null;
             data = queue.poll())
            System.out.print(data + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        PriorityQueue<Double> priorityQueue =
                new PriorityQueue<Double>();
        for (int i = 0; i < 10; i++)
            priorityQueue.offer(rand.nextDouble());
        printQ(priorityQueue);
    }
}