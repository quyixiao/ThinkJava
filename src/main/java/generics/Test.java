package generics;

import java.util.Random;


/****
 *
 *  10亿个数中的最小的10个数
 *
 *
 *
 *
 *  1
 *
 *
 */
public class Test {
    static Random random = new Random();

    static class Node {
        int value;
        Node next;
        Node pre;

        public Node(int value) {
            this.value = value;
        }
    }

    static class Result {
        Node head;
        Node tail;
        int length;

        public Result(int length) {
            this.length = length;
            this.head = new Node(Integer.MAX_VALUE);
            this.tail = new Node(Integer.MAX_VALUE);
            Node temp = head;
            for (; length > 2; length--) {
                Node t = new Node(Integer.MAX_VALUE);
                temp.next = t;
                t.pre = temp;
                temp = t;
            }
            temp.next = tail;
            tail.pre = temp;
        }
    }

    public static void main(String[] args) {
        Result result = new Result(10);
        long st = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            int tempNum = random.nextInt(Integer.MAX_VALUE);
            if (tempNum < result.head.value) {
                Node tempNode = new Node(tempNum);
                tempNode.next = result.head;
                result.head.pre = tempNode;
                result.head = tempNode;
                result.tail = result.tail.pre;
                result.tail.next = null;
            } else if (tempNum < result.tail.value) {
                Node currNode = result.tail;
                while (currNode != null) {
                    if (currNode.value == tempNum) {
                        break;
                    } else if (tempNum > currNode.value) {
                        Node tempNode = new Node(tempNum);
                        Node nextNode = currNode.next;
                        currNode.next = tempNode;
                        tempNode.next = nextNode;
                        tempNode.pre = currNode;
                        nextNode.pre = tempNode;
                        result.tail = result.tail.pre;
                        result.tail.next = null;
                        break;
                    }
                    currNode = currNode.pre;
                }
            }
        }
        long et = System.currentTimeMillis();
        Node cN = result.head;
        while (cN != null) {
            System.out.println(cN.value);
            cN = cN.next;
        }
        System.out.println("用时:" + (et - st) + "ms");
    }
}