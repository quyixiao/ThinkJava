package holding;

import java.util.PriorityQueue;


/*****
 *
 * 238页
 *      创建一个继承自Object的简单类，它不包含任何成员，展示你不能将这个类的
 *  多个示例成功地添加到一个PriorityQueue中，这个问题将在第17章中详细解释
 *
 */
class Dummy {
}

public class E29_PriorityQueueSubtlety {
    public static void main(String[] args) {
        PriorityQueue<Dummy> priorityQueue =
                new PriorityQueue<Dummy>();
        System.out.println("Adding 1st instance...");
        priorityQueue.offer(new Dummy());
        System.out.println("Adding 2nd instance...");
        priorityQueue.offer(new Dummy());
    }
}