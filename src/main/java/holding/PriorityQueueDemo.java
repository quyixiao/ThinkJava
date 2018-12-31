package holding;//: holding/PriorityQueueDemo.java

import java.util.*;


/****
 *
 * 237
 *      让priorityQueue与intege，String和Ccharacter这样的内置类型一起工作易如反掌，在下面
 *  的示例中，第一个值集与前一个的随机值相同，因此你可以看到它们从proorityQueue中弹出的顺序
 *  与前一个示例不同
 *
 *
 *  1
 */
public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
        Random rand = new Random(47);
        for (int i = 0; i < 10; i++) {
            priorityQueue.offer(rand.nextInt(i + 10));
        }
        QueueDemo.printQ(priorityQueue);

        System.out.println("=========2======");
        List<Integer> ints = Arrays.asList(25, 22, 20, 18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);
        priorityQueue = new PriorityQueue<Integer>(ints);
        QueueDemo.printQ(priorityQueue);

        System.out.println("==========3======");

        priorityQueue = new PriorityQueue<Integer>(ints.size(), Collections.reverseOrder());
        priorityQueue.addAll(ints);
        QueueDemo.printQ(priorityQueue);
        System.out.println("===========4=====");

        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(""));
        PriorityQueue<String> stringPQ = new PriorityQueue<String>(strings);
        QueueDemo.printQ(stringPQ);
        stringPQ = new PriorityQueue<String>(strings.size(), Collections.reverseOrder());
        System.out.println("============5====");
        stringPQ.addAll(strings);
        QueueDemo.printQ(stringPQ);

        System.out.println("============6====");

        Set<Character> charSet = new HashSet<Character>();
        for (char c : fact.toCharArray())
            charSet.add(c); // Autoboxing
        PriorityQueue<Character> characterPQ = new PriorityQueue<Character>(charSet);
        QueueDemo.printQ(characterPQ);
    }
}



/* Output:
0 1 1 1 1 1 3 5 8 14
1 1 2 3 3 9 9 14 14 18 18 20 21 22 23 25 25
25 25 23 22 21 20 18 18 14 14 9 9 3 3 2 1 1
       A A B C C C D D E E E F H H I I L N N O O O O S S S T T U U U W
W U U U T T S S S O O O O N N L I I H H F E E E D D C C C B A A
  A B C D E F H I L N O S T U W
*///:~
